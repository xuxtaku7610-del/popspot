package com.popspot.app.data.di

import com.popspot.app.data.remote.api.NaverBlogApi
import com.popspot.app.data.remote.api.TourApi
import com.popspot.app.data.repository.MapRepositoryImpl
import com.popspot.app.data.repository.PopupRepositoryImpl
import com.popspot.app.data.repository.PublicDataRepositoryImpl
import com.popspot.app.data.repository.TourRepositoryImpl
import com.popspot.app.domain.repository.MapRepository
import com.popspot.app.domain.repository.PopupRepository
import com.popspot.app.domain.repository.PublicDataRepository
import com.popspot.app.domain.repository.TourRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.popspot.app.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BASIC
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()

    // Naver Blog Search API — headers injected per-call via @Header params
    @Provides
    @Singleton
    @Named("naver")
    fun provideNaverRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 한국관광공사 TourAPI (공공데이터 포털)
    @Provides
    @Singleton
    @Named("tour")
    fun provideTourRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNaverBlogApi(@Named("naver") retrofit: Retrofit): NaverBlogApi =
        retrofit.create(NaverBlogApi::class.java)

    @Provides
    @Singleton
    fun provideTourApi(@Named("tour") retrofit: Retrofit): TourApi =
        retrofit.create(TourApi::class.java)

    // Repository bindings
    @Provides
    @Singleton
    fun providePopupRepository(impl: PopupRepositoryImpl): PopupRepository = impl

    @Provides
    @Singleton
    fun provideTourRepository(impl: TourRepositoryImpl): TourRepository = impl

    // Kept for backward-compatibility with existing skeleton ViewModels
    @Provides
    @Singleton
    fun provideMapRepository(impl: MapRepositoryImpl): MapRepository = impl

    @Provides
    @Singleton
    fun providePublicDataRepository(impl: PublicDataRepositoryImpl): PublicDataRepository = impl
}
