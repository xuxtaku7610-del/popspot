package com.popspot.app.data.remote.api

import com.popspot.app.data.remote.dto.NaverGeocodingDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMapApi {
    @GET("map-geocode/v2/geocode")
    suspend fun geocodeAddress(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
        @Query("query") address: String
    ): NaverGeocodingDto
}
