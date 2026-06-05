package com.popspot.app.domain.model

data class TourEvent(
    val id: String,
    val title: String,
    val place: String,
    val startDate: String,      // yyyyMMdd
    val endDate: String,
    val imageUrl: String,
    val description: String,
    val category: String,       // "축제", "문화행사" 등
    val isOfficial: Boolean = true
)
