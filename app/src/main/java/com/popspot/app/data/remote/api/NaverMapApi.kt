package com.popspot.app.data.remote.api

import com.popspot.app.data.remote.dto.NaverGeocodingDto
import com.popspot.app.data.remote.dto.NaverReverseGeocodingDto
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

    @GET("map-reversegeocode/v2/gc")
    suspend fun reverseGeocode(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
        @Query("coords") coords: String,
        @Query("orders") orders: String = "roadaddr,addr",
        @Query("output") output: String = "json"
    ): NaverReverseGeocodingDto
}
