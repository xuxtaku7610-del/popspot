package com.popspot.app.domain.repository

import com.popspot.app.domain.model.PopupPost

interface PopupRepository {
    suspend fun searchPopupPosts(query: String): Result<List<PopupPost>>
    suspend fun getLatestPopupPosts(): Result<List<PopupPost>>
}
