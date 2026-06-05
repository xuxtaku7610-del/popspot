package com.popspot.app.data.remote.api

import com.popspot.app.data.remote.dto.PublicDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PublicDataApi {
    @GET("events")
    suspend fun getEvents(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: Int = 20,
        @Query("pageNo") pageNo: Int = 1
    ): PublicDataDto
}
