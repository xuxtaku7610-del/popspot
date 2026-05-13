package com.popspot.app.data.repository

import com.popspot.app.domain.model.Event
import com.popspot.app.domain.repository.PublicDataRepository
import javax.inject.Inject

// Superseded by TourRepositoryImpl; kept so existing bindings compile
class PublicDataRepositoryImpl @Inject constructor() : PublicDataRepository {
    override suspend fun getEvents(): List<Event> = emptyList()
}
