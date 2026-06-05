package com.popspot.app.data.repository

import com.popspot.app.data.local.ScrapDao
import com.popspot.app.data.local.ScrapEntity
import com.popspot.app.domain.model.ScrapItem
import com.popspot.app.domain.repository.ScrapRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScrapRepositoryImpl @Inject constructor(
    private val scrapDao: ScrapDao
) : ScrapRepository {

    override fun getAllScraps(): Flow<List<ScrapItem>> =
        scrapDao.getAllScraps().map { entities -> entities.map { it.toScrapItem() } }

    override suspend fun insertScrap(item: ScrapItem) {
        scrapDao.insert(item.toEntity())
    }

    override suspend fun deleteScrap(id: String) {
        scrapDao.delete(id)
    }

    override suspend fun isScapped(id: String): Boolean =
        scrapDao.isScrapped(id)

    private fun ScrapEntity.toScrapItem(): ScrapItem = ScrapItem(
        id       = id,
        title    = title,
        source   = source,
        imageUrl = imageUrl,
        date     = date,
        link     = link
    )

    private fun ScrapItem.toEntity(): ScrapEntity = ScrapEntity(
        id       = id,
        title    = title,
        source   = source,
        imageUrl = imageUrl,
        date     = date,
        link     = link
    )
}
