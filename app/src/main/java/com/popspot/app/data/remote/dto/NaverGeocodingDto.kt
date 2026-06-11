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

// Reverse Geocoding DTO: 좌표 -> 행정구역/주소
// Naver Reverse Geocoding API 응답 중 화면 표시와 행정구역 추출에 필요한 필드만 정의

data class NaverReverseGeocodingDto(
    @SerializedName("status") val reverseStatus: ReverseGeocodingStatus = ReverseGeocodingStatus(),
    @SerializedName("results") val results: List<ReverseGeocodingResult> = emptyList()
)

data class ReverseGeocodingStatus(
    @SerializedName("code") val code: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("message") val message: String = ""
)

data class ReverseGeocodingResult(
    @SerializedName("name") val name: String = "",
    @SerializedName("region") val region: ReverseRegion? = null,
    @SerializedName("land") val land: ReverseLand? = null
)

data class ReverseRegion(
    @SerializedName("area1") val area1: ReverseArea? = null,
    @SerializedName("area2") val area2: ReverseArea? = null,
    @SerializedName("area3") val area3: ReverseArea? = null,
    @SerializedName("area4") val area4: ReverseArea? = null
)

data class ReverseArea(
    @SerializedName("name") val name: String = ""
)

data class ReverseLand(
    @SerializedName("type") val type: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("number1") val number1: String = "",
    @SerializedName("number2") val number2: String = ""
)
