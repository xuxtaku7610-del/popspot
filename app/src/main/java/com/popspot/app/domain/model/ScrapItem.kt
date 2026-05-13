package com.popspot.app.domain.model

data class ScrapItem(
    val id: String,
    val title: String,
    val source: String,     // "NAVER_BLOG" or "TOUR_API"
    val imageUrl: String,
    val date: String,
    val link: String
)
