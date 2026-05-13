package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.TourEvent
import com.popspot.app.domain.repository.TourRepository
import javax.inject.Inject

class GetOfficialEventsUseCase @Inject constructor(
    private val tourRepository: TourRepository
) {
    suspend operator fun invoke(
        areaCode: String = "",
        startDate: String = ""
    ): Result<List<TourEvent>> = tourRepository.getOfficialEvents(areaCode, startDate)
}
