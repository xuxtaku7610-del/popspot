package com.popspot.app.presentation.nearby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.popspot.app.domain.model.PopupStore
import com.popspot.app.domain.usecase.GetCurrentLocationUseCase
import com.popspot.app.domain.usecase.GetPopupStoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// UI 상태 — Loading(초기) / Success(위치 확보) / PermissionDenied / Error
sealed class NearbyUiState {
    object Loading : NearbyUiState()
    data class Success(val location: LatLng) : NearbyUiState()
    object PermissionDenied : NearbyUiState()
    data class Error(val message: String) : NearbyUiState()
}

@HiltViewModel
class NearbyViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getPopupStoresUseCase: GetPopupStoresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NearbyUiState>(NearbyUiState.Loading)
    val uiState: StateFlow<NearbyUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedStore = MutableStateFlow<PopupStore?>(null)
    val selectedStore: StateFlow<PopupStore?> = _selectedStore.asStateFlow()

    private val _allStores = MutableStateFlow<List<PopupStore>>(emptyList())

    private val _filteredStores = MutableStateFlow<List<PopupStore>>(emptyList())
    val filteredStores: StateFlow<List<PopupStore>> = _filteredStores.asStateFlow()

    init {
        loadPopupStores()
    }

    private fun loadPopupStores() {
        viewModelScope.launch {
            runCatching { getPopupStoresUseCase() }
                .onSuccess { stores ->
                    _allStores.value = stores
                    applySearchFilter(_searchQuery.value)
                }
                .onFailure {
                    _allStores.value = emptyList()
                    _filteredStores.value = emptyList()
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _selectedStore.value = null
        applySearchFilter(query)
    }

    fun onMarkerClicked(store: PopupStore) {
        _selectedStore.value = store
    }

    fun onBottomSheetDismissed() {
        _selectedStore.value = null
    }

    private fun applySearchFilter(query: String) {
        val trimmedQuery = query.trim()
        _filteredStores.value = if (trimmedQuery.isBlank()) {
            _allStores.value
        } else {
            _allStores.value.filter { store ->
                store.name.contains(trimmedQuery, ignoreCase = true) ||
                        store.category.contains(trimmedQuery, ignoreCase = true) ||
                        store.description.contains(trimmedQuery, ignoreCase = true) ||
                        store.location.address.contains(trimmedQuery, ignoreCase = true)
            }
        }
    }

    // NearbyScreen이 권한 허용 확인 후 호출
    fun onPermissionGranted() {
        viewModelScope.launch {
            _uiState.value = NearbyUiState.Loading
            try {
                val location = getCurrentLocationUseCase()
                _uiState.value = NearbyUiState.Success(
                    LatLng(location.latitude, location.longitude)
                )
            } catch (e: Exception) {
                _uiState.value = NearbyUiState.Error(
                    e.message ?: "위치를 가져올 수 없습니다. 서울 중심으로 표시합니다."
                )
            }
        }
    }

    // NearbyScreen이 권한 거부 확인 후 호출
    fun onPermissionDenied() {
        _uiState.value = NearbyUiState.PermissionDenied
    }
}
