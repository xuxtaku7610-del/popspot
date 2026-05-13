package com.popspot.app.domain.model

data class PopupPost(
    val id: String,
    val title: String,          // HTML tags stripped
    val description: String,    // HTML tags stripped
    val bloggerName: String,    // 블로그명
    val postDate: String,       // yyyyMMdd
    val link: String,           // 원문 링크
    val isScrapped: Boolean = false
)
