package com.popspot.app.presentation.scrap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.domain.repository.ScrapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// NOTE: spec names this List<PopupStore> but ScrapItem is the actual persisted model.
// Using ScrapItem here because that is what Room stores and ScrapRepository emits.
// Rename when a unified feed model is introduced.

@HiltViewModel
class ScrapViewModel @Inject constructor(
    private val scrapRepository: ScrapRepository
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow("전체")
    val selectedFilter: StateFlow<String> = _selectedFilter.asStateFlow()

    // Raw Room stream — updates automatically whenever the DB changes
    private val _allScraps: StateFlow<List<ScrapItem>> = scrapRepository.getAllScraps()
        .stateIn(
            scope        = viewModelScope,
            started      = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // Derived — filtered by selectedFilter; chip label maps to ScrapItem.source value
    val scrappedItems: StateFlow<List<ScrapItem>> = combine(
        _allScraps,
        _selectedFilter
    ) { all, filter ->
        when (filter) {
            "팝업"    -> all.filter { it.source == "NAVER_BLOG" }
            "공식 행사" -> all.filter { it.source == "TOUR_API" }
            else      -> all   // "전체" — no filter
        }
    }.stateIn(
        scope        = viewModelScope,
        started      = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun filterItems(filter: String) {
        _selectedFilter.value = filter
    }

    fun removeScrap(id: String) {
        viewModelScope.launch {
            scrapRepository.deleteScrap(id)
            // No explicit state update needed — _allScraps is a live Room Flow
        }
    }
}
