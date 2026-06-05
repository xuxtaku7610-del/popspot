package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.Location
import com.popspot.app.domain.repository.NearbyRepository
import javax.inject.Inject

// Domain 레이어 — Android SDK 의존 없는 순수 Kotlin UseCase
class GetCurrentLocationUseCase @Inject constructor(
    private val repository: NearbyRepository
) {
    suspend operator fun invoke(): Location = repository.getCurrentLocation()
}
