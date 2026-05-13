package com.popspot.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scraps")
data class ScrapEntity(
    @PrimaryKey val id: String,
    val title: String,
    val source: String,
    val imageUrl: String,
    val date: String,
    val link: String
)
