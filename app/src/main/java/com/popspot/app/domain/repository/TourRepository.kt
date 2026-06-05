package com.popspot.app.domain.repository

import com.popspot.app.domain.model.TourEvent

interface TourRepository {
    suspend fun getOfficialEvents(
        areaCode: String = "",
        startDate: String = ""
    ): Result<List<TourEvent>>

    suspend fun getEventDetail(contentId: String): Result<TourEvent>
}
