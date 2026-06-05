package com.popspot.app.domain.repository

import com.popspot.app.domain.model.Location

// Domain 레이어 — Android SDK 의존 없음, 순수 Kotlin 인터페이스
interface NearbyRepository {
    // 현재 디바이스 위치를 반환. 권한 체크는 호출자(Screen) 책임
    suspend fun getCurrentLocation(): Location
}
