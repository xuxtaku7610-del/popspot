package com.popspot.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.model.Event
import com.popspot.app.domain.model.PopupStore
import com.popspot.app.domain.usecase.GetEventsUseCase
import com.popspot.app.domain.usecase.GetPopupStoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Entire screen state in one immutable snapshot — no separate loading flags per section
data class HomeUiState(
    val hotStores: List<PopupStore> = emptyList(),      // drives featured banner
    val trendStores: List<PopupStore> = emptyList(),    // drives horizontal trend row
    val officialEvents: List<Event> = emptyList(),      // drives official event card
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopupStoresUseCase: GetPopupStoresUseCase,
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Fan out both requests concurrently — halves perceived load time
                val storesDeferred = async { getPopupStoresUseCase() }
                val eventsDeferred = async { getEventsUseCase() }

                val stores = storesDeferred.await()
                val events = eventsDeferred.await()

                _uiState.update {
                    it.copy(
                        hotStores      = stores,   // banner shows firstOrNull()
                        trendStores    = stores,   // trend row takes first 3
                        officialEvents = events,
                        isLoading      = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "알 수 없는 오류가 발생했어요")
                }
            }
        }
    }
}
