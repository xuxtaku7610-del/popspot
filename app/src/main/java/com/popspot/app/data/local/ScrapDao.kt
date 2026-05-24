package com.popspot.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScrapDao {

    @Query("SELECT * FROM scraps ORDER BY rowid DESC")
    fun getAllScraps(): Flow<List<ScrapEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ScrapEntity)

    @Query("DELETE FROM scraps WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT COUNT(*) > 0 FROM scraps WHERE id = :id")
    suspend fun isScrapped(id: String): Boolean
}
