package com.popspot.app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NaverBlogResponse(
    @SerializedName("lastBuildDate") val lastBuildDate: String = "",
    @SerializedName("total")         val total: Int = 0,
    @SerializedName("start")         val start: Int = 0,
    @SerializedName("display")       val display: Int = 0,
    @SerializedName("items")         val items: List<NaverBlogItem> = emptyList()
)

data class NaverBlogItem(
    @SerializedName("title")       val title: String = "",         // <b> 태그 포함 — 정제 필요
    @SerializedName("link")        val link: String = "",
    @SerializedName("description") val description: String = "",   // <b> 태그 포함 — 정제 필요
    @SerializedName("bloggername") val bloggername: String = "",
    @SerializedName("bloggerlink") val bloggerlink: String = "",
    @SerializedName("postdate")    val postdate: String = ""       // yyyyMMdd
)
