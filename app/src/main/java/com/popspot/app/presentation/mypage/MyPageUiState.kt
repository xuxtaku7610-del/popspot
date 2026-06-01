package com.popspot.app.presentation.mypage

// 마이페이지 UI 상태 모델
data class MyPageUiState(
    val scrapCount: Int = 0,              // 스크랩 수 — Room DB ScrapRepository에서 실시간 수신
    val recentSearchCount: Int = 0,       // 최근 검색 수 — 추후 Room 연동 예정
    val reviewCount: Int = 0,            // 내 리뷰 수 — 추후 서버 연동 예정
    val notificationEnabled: Boolean = true, // 알림 설정 — 추후 DataStore 연동 예정
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
