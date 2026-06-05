package com.popspot.app.data.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.popspot.app.data.repository.NearbyRepositoryImpl
import com.popspot.app.domain.repository.NearbyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    // FusedLocationProviderClient는 Context가 필요하므로 @ApplicationContext 사용
    // SingletonComponent이므로 앱 수명과 동일하게 유지
    @Provides
    @Singleton
    fun provideFusedLocationClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // NearbyRepository 인터페이스 → NearbyRepositoryImpl 구현체 바인딩
    @Provides
    @Singleton
    fun provideNearbyRepository(impl: NearbyRepositoryImpl): NearbyRepository = impl
}
