package com.popspot.app.presentation.popup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.model.PopupPost
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.domain.repository.ScrapRepository
import com.popspot.app.domain.usecase.GetLatestPopupPostsUseCase
import com.popspot.app.domain.usecase.ToggleScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Remote fetch state — search/chip state kept separate so filter is purely local
data class PopupFeedUiState(
    val allPosts: List<PopupPost> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PopupFeedViewModel @Inject constructor(
    private val getLatestPopupPostsUseCase: GetLatestPopupPostsUseCase,
    private val toggleScrapUseCase: ToggleScrapUseCase,
    scrapRepository: ScrapRepository
) : ViewModel() {

    // NOTE: spec says List<Event> but PopupPost is used here because:
    //  - Event has no `link` field → "원문 보기 ↗" cannot be built from Event
    //  - This screen is powered by NaverBlogApi which maps → PopupPost
    // Update this type when a unified feed model is introduced.

    private val _uiState = MutableStateFlow(PopupFeedUiState())
    val uiState: StateFlow<PopupFeedUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedChip = MutableStateFlow("전체")
    val selectedChip: StateFlow<String> = _selectedChip.asStateFlow()

    // Room에 저장된 스크랩 id 목록. 팝업 카드의 하트 상태를 표시하는 데 사용한다.
    val scrappedIds: StateFlow<Set<String>> = scrapRepository.getAllScraps()
        .map { scraps -> scraps.map { it.id }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptySet()
        )

    // Derived StateFlow — recomputed whenever posts, query, or chip changes.
    // `WhileSubscribed(5_000)` cancels the upstream after 5 s with no collectors
    // (e.g. screen rotation) while keeping state through brief interruptions.
    val filteredItems: StateFlow<List<PopupPost>> = combine(
        _uiState,
        _searchQuery,
        _selectedChip
    ) { state, query, chip ->
        state.allPosts.filter { post ->
            // Both conditions must pass; blank query / "전체" chip are free passes
            val matchesQuery = query.isBlank()
                || post.title.contains(query, ignoreCase = true)
                || post.description.contains(query, ignoreCase = true)

            val matchesChip = chip == "전체"
                || post.title.contains(chip, ignoreCase = true)
                || post.description.contains(chip, ignoreCase = true)

            matchesQuery && matchesChip
        }
    }.stateIn(
        scope        = viewModelScope,
        started      = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        loadPosts()
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
    }

    fun onChipSelected(chip: String) {
        _selectedChip.value = chip
    }

    fun toggleScrap(post: PopupPost) {
        viewModelScope.launch {
            toggleScrapUseCase(
                ScrapItem(
                    id = post.id,
                    title = post.title,
                    source = "NAVER_BLOG",
                    imageUrl = "",
                    date = post.postDate,
                    link = post.link
                )
            )
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getLatestPopupPostsUseCase()
                .onSuccess { posts ->
                    _uiState.update { it.copy(allPosts = posts, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "데이터를 불러오지 못했어요")
                    }
                }
        }
    }
}
