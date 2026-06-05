package com.popspot.app.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.model.PopupStore
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.domain.repository.ScrapRepository
import com.popspot.app.domain.usecase.GetPopupStoresUseCase
import com.popspot.app.domain.usecase.ToggleScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,              // auto-populated with nav args by Hilt
    private val getPopupStoresUseCase: GetPopupStoresUseCase,
    private val scrapRepository: ScrapRepository,
    private val toggleScrapUseCase: ToggleScrapUseCase
) : ViewModel() {

    // "{id}" segment extracted from the "detail/{id}" route
    private val storeId: String = savedStateHandle.get<String>("id") ?: ""

    private val _popupStore = MutableStateFlow<PopupStore?>(null)
    val popupStore: StateFlow<PopupStore?> = _popupStore.asStateFlow()

    private val _isScrapped = MutableStateFlow(false)
    val isScrapped: StateFlow<Boolean> = _isScrapped.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        if (storeId.isNotBlank()) loadDetail(storeId)
    }

    fun loadDetail(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _popupStore.value = getPopupStoresUseCase().find { it.id == id }
                _isScrapped.value = scrapRepository.isScapped(id)
            } catch (e: Exception) {
                _error.value = e.message ?: "데이터를 불러오지 못했어요"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleScrap() {
        val store = _popupStore.value ?: return
        viewModelScope.launch {
            // Build a ScrapItem from the current PopupStore so ToggleScrapUseCase can persist it
            val scrapItem = ScrapItem(
                id       = store.id,
                title    = store.name,
                source   = "NAVER_BLOG",
                imageUrl = store.imageUrl,
                date     = store.startDate,
                link     = ""
            )
            toggleScrapUseCase(scrapItem)
            // Re-query Room as the source of truth rather than flipping the flag optimistically
            _isScrapped.value = scrapRepository.isScapped(store.id)
        }
    }
}
