package com.popspot.app.data.repository

import com.popspot.app.domain.model.Location
import com.popspot.app.domain.repository.MapRepository
import javax.inject.Inject

// Geocoding is handled client-side via Naver Map SDK; this impl is a placeholder
class MapRepositoryImpl @Inject constructor() : MapRepository {
    override suspend fun getLocation(address: String): Location =
        Location(latitude = 0.0, longitude = 0.0, address = address)
}
