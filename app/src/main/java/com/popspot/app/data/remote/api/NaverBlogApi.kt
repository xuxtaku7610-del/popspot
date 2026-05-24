package com.popspot.app.data.remote.api

import com.popspot.app.BuildConfig
import com.popspot.app.data.remote.dto.NaverBlogResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverBlogApi {
    @GET("v1/search/blog.json")
    suspend fun searchBlog(
        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.NAVER_CLIENT_ID,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.NAVER_CLIENT_SECRET,
        @Query("query") query: String,
        @Query("display") display: Int = 10,
        @Query("start") start: Int = 1,
        @Query("sort") sort: String = "date"   // 최신순
    ): NaverBlogResponse
}
