package com.popspot.app.domain.repository

import com.popspot.app.domain.model.Location

interface MapRepository {
    suspend fun getLocation(address: String): Location
}
