package com.popspot.app.data.repository

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.popspot.app.domain.model.Location
import com.popspot.app.domain.repository.NearbyRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class NearbyRepositoryImpl @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) : NearbyRepository {

    // @SuppressLint: 권한 체크를 NearbyScreen(Compose 레이어)에서 수행하므로
    // 이 지점 도달 시 이미 ACCESS_FINE_LOCATION이 허용된 상태
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { androidLocation ->
                    if (androidLocation != null) {
                        continuation.resume(
                            Location(
                                latitude  = androidLocation.latitude,
                                longitude = androidLocation.longitude
                            )
                        )
                    } else {
                        // lastLocation이 null이면 서울 시청으로 fallback
                        // (디바이스가 최근에 위치를 캐시하지 않은 경우 발생)
                        continuation.resume(Location(latitude = 37.5666, longitude = 126.9782))
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
}
