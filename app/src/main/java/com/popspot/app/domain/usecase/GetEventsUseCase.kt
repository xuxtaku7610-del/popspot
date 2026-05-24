package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.Event
import com.popspot.app.domain.repository.PublicDataRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: PublicDataRepository
) {
    suspend operator fun invoke(): List<Event> = repository.getEvents()
}
