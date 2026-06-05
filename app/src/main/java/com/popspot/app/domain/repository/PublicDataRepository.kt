package com.popspot.app.domain.repository

import com.popspot.app.domain.model.Event

interface PublicDataRepository {
    suspend fun getEvents(): List<Event>
}
