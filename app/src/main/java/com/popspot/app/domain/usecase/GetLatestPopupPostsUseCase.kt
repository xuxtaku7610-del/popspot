package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.PopupPost
import com.popspot.app.domain.repository.PopupRepository
import javax.inject.Inject

class GetLatestPopupPostsUseCase @Inject constructor(
    private val popupRepository: PopupRepository
) {
    suspend operator fun invoke(): Result<List<PopupPost>> =
        popupRepository.getLatestPopupPosts()
}
