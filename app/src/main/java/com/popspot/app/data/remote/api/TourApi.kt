package com.popspot.app.data.remote.api

import com.popspot.app.BuildConfig
import com.popspot.app.data.remote.dto.TourApiResponse
import com.popspot.app.data.remote.dto.TourDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TourApi {
    // 행사정보조회
    @GET("B551011/KorService1/searchFestival1")
    suspend fun getEventList(
        @Query("serviceKey") serviceKey: String = BuildConfig.TOUR_API_KEY,
        @Query("numOfRows") numOfRows: Int = 20,
        @Query("pageNo") pageNo: Int = 1,
        @Query("MobileOS") mobileOS: String = "AND",
        @Query("MobileApp") mobileApp: String = "PopSpot",
        @Query("_type") type: String = "json",
        @Query("listYN") listYN: String = "Y",
        @Query("arrange") arrange: String = "A",   // 정렬: A=제목순
        @Query("eventStartDate") eventStartDate: String = "",
        @Query("areaCode") areaCode: String = ""
    ): TourApiResponse

    // 공통정보조회 (상세)
    @GET("B551011/KorService1/detailCommon1")
    suspend fun getEventDetail(
        @Query("serviceKey") serviceKey: String = BuildConfig.TOUR_API_KEY,
        @Query("contentId") contentId: String,
        @Query("MobileOS") mobileOS: String = "AND",
        @Query("MobileApp") mobileApp: String = "PopSpot",
        @Query("_type") type: String = "json",
        @Query("defaultYN") defaultYN: String = "Y",
        @Query("firstImageYN") firstImageYN: String = "Y",
        @Query("overviewYN") overviewYN: String = "Y"
    ): TourDetailResponse
}
