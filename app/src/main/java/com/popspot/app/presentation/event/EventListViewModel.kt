package com.popspot.app.presentation.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.model.Event
import com.popspot.app.domain.usecase.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Remote fetch state — filter state kept separate so filtering is purely local
data class EventListUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventListUiState())
    val uiState: StateFlow<EventListUiState> = _uiState.asStateFlow()

    private val _allEvents = MutableStateFlow<List<Event>>(emptyList())

    private val _selectedCity = MutableStateFlow("서울")
    val selectedCity: StateFlow<String> = _selectedCity.asStateFlow()

    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate: StateFlow<String?> = _selectedDate.asStateFlow()

    // Derived — recomputed whenever allEvents, city, or date changes.
    // "서울" is the default sentinel meaning "no city filter" since TourAPI data is Seoul-centric.
    // Date comparison works as plain string because "yyyyMMdd" is lexicographically ordered.
    val events: StateFlow<List<Event>> = combine(
        _allEvents,
        _selectedCity,
        _selectedDate
    ) { allEvents, city, date ->
        allEvents
            .filter { event ->
                city == "서울" || event.place.contains(city, ignoreCase = true)
            }
            .filter { event ->
                date == null
                    || (event.startDate.isNotBlank()
                    && event.endDate.isNotBlank()
                    && event.startDate <= date
                    && date <= event.endDate)
            }
    }.stateIn(
        scope        = viewModelScope,
        started      = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        loadEvents()
    }

    fun filterByCity(city: String) {
        _selectedCity.value = city
    }

    fun filterByDate(date: String?) {
        _selectedDate.value = date
    }

    private fun loadEvents() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                _allEvents.value = getEventsUseCase()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message ?: "데이터를 불러오지 못했어요")
                }
            }
        }
    }
}
