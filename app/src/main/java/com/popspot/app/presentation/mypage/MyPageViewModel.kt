package com.popspot.app.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popspot.app.domain.repository.ScrapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val scrapRepository: ScrapRepository
) : ViewModel() {

    private val _notificationEnabled = MutableStateFlow(true)

    // Room 스크랩 DB를 실시간 구독 → 개수로 변환
    private val _scrapCount: StateFlow<Int> = scrapRepository.getAllScraps()
        .map { it.size }
        .stateIn(
            scope        = viewModelScope,
            started      = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    // 알림 상태와 스크랩 수를 하나의 UiState로 결합
    val uiState: StateFlow<MyPageUiState> = combine(
        _scrapCount,
        _notificationEnabled
    ) { scrapCount, notificationEnabled ->
        MyPageUiState(
            scrapCount          = scrapCount,
            recentSearchCount   = 0,   // 추후 검색 기록 Room 연동
            reviewCount         = 0,   // 추후 리뷰 서버 연동
            notificationEnabled = notificationEnabled
        )
    }.stateIn(
        scope        = viewModelScope,
        started      = SharingStarted.WhileSubscribed(5_000),
        initialValue = MyPageUiState()
    )

    fun toggleNotification() {
        _notificationEnabled.update { !it }
    }
}
