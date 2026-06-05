package com.popspot.app.data.remote.dto

import com.google.gson.annotations.SerializedName

// ─── Event list response ─────────────────────────────────────────────────────

data class TourApiResponse(
    @SerializedName("response") val response: TourResponse
)

data class TourResponse(
    @SerializedName("header") val header: TourHeader,
    @SerializedName("body")   val body: TourBody
)

data class TourHeader(
    @SerializedName("resultCode") val resultCode: String = "",
    @SerializedName("resultMsg")  val resultMsg: String = ""
)

data class TourBody(
    @SerializedName("items")      val items: TourItems,
    @SerializedName("totalCount") val totalCount: Int = 0
)

data class TourItems(
    @SerializedName("item") val item: List<TourItem>?
)

data class TourItem(
    @SerializedName("contentid")       val contentid: String = "",
    @SerializedName("title")           val title: String = "",
    @SerializedName("addr1")           val addr1: String? = null,
    @SerializedName("firstimage")      val firstimage: String? = null,
    @SerializedName("firstimage2")     val firstimage2: String? = null,
    @SerializedName("eventstartdate")  val eventstartdate: String? = null,
    @SerializedName("eventenddate")    val eventenddate: String? = null,
    @SerializedName("cat1")            val cat1: String? = null,
    @SerializedName("cat2")            val cat2: String? = null,
    @SerializedName("cat3")            val cat3: String? = null
)

// ─── Event detail response ───────────────────────────────────────────────────

data class TourDetailResponse(
    @SerializedName("response") val response: TourDetailWrapper
)

data class TourDetailWrapper(
    @SerializedName("header") val header: TourHeader,
    @SerializedName("body")   val body: TourDetailBody
)

data class TourDetailBody(
    @SerializedName("items")      val items: TourDetailItems,
    @SerializedName("totalCount") val totalCount: Int = 0
)

data class TourDetailItems(
    @SerializedName("item") val item: List<TourDetailItem>?
)

data class TourDetailItem(
    @SerializedName("contentid")      val contentid: String = "",
    @SerializedName("title")          val title: String? = null,
    @SerializedName("firstimage")     val firstimage: String? = null,
    @SerializedName("firstimage2")    val firstimage2: String? = null,
    @SerializedName("overview")       val overview: String? = null,
    @SerializedName("addr1")          val addr1: String? = null,
    @SerializedName("eventstartdate") val eventstartdate: String? = null,
    @SerializedName("eventenddate")   val eventenddate: String? = null
)
