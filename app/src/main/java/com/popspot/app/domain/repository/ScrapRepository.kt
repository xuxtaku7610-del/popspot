package com.popspot.app.domain.repository

import com.popspot.app.domain.model.ScrapItem
import kotlinx.coroutines.flow.Flow

interface ScrapRepository {
    fun getAllScraps(): Flow<List<ScrapItem>>
    suspend fun insertScrap(item: ScrapItem)
    suspend fun deleteScrap(id: String)
    suspend fun isScapped(id: String): Boolean
}
