package com.popspot.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScrapEntity::class], version = 1, exportSchema = false)
abstract class PopSpotDatabase : RoomDatabase() {
    abstract fun scrapDao(): ScrapDao
}
