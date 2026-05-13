package com.popspot.app.domain.usecase

import com.popspot.app.domain.model.PopupPost
import com.popspot.app.domain.repository.PopupRepository
import javax.inject.Inject

class SearchPopupPostsUseCase @Inject constructor(
    private val popupRepository: PopupRepository
) {
    suspend operator fun invoke(query: String): Result<List<PopupPost>> =
        popupRepository.searchPopupPosts(query)
}
