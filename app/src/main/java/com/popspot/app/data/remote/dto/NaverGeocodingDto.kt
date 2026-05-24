package com.popspot.app.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NaverGeocodingDto(
    @SerializedName("status") val status: String = "",
    @SerializedName("meta") val meta: GeocodingMeta = GeocodingMeta(),
    @SerializedName("addresses") val addresses: List<GeocodingAddress> = emptyList()
)

data class GeocodingMeta(
    @SerializedName("totalCount") val totalCount: Int = 0,
    @SerializedName("page") val page: Int = 0,
    @SerializedName("count") val count: Int = 0
)

data class GeocodingAddress(
    @SerializedName("roadAddress") val roadAddress: String = "",
    @SerializedName("jibunAddress") val jibunAddress: String = "",
    @SerializedName("x") val longitude: String = "",
    @SerializedName("y") val latitude: String = ""
)
