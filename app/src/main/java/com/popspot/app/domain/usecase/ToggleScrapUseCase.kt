package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.domain.repository.ScrapRepository
import javax.inject.Inject

class ToggleScrapUseCase @Inject constructor(
    private val scrapRepository: ScrapRepository
) {
    suspend operator fun invoke(item: ScrapItem) {
        if (scrapRepository.isScapped(item.id)) {
            scrapRepository.deleteScrap(item.id)
        } else {
            scrapRepository.insertScrap(item)
        }
    }
}
