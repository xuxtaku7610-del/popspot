package com.popspot.app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PublicDataDto(
    @SerializedName("response") val response: PublicDataResponse = PublicDataResponse()
)

data class PublicDataResponse(
    @SerializedName("header") val header: PublicDataHeader = PublicDataHeader(),
    @SerializedName("body") val body: PublicDataBody = PublicDataBody()
)

data class PublicDataHeader(
    @SerializedName("resultCode") val resultCode: String = "",
    @SerializedName("resultMsg") val resultMsg: String = ""
)

data class PublicDataBody(
    @SerializedName("items") val items: List<PublicDataItem> = emptyList(),
    @SerializedName("numOfRows") val numOfRows: Int = 0,
    @SerializedName("pageNo") val pageNo: Int = 0,
    @SerializedName("totalCount") val totalCount: Int = 0
)

data class PublicDataItem(
    @SerializedName("eventId") val eventId: String = "",
    @SerializedName("eventName") val eventName: String = "",
    @SerializedName("eventPlace") val eventPlace: String = "",
    @SerializedName("startDate") val startDate: String = "",
    @SerializedName("endDate") val endDate: String = ""
)
