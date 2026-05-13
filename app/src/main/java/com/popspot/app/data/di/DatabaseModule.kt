package com.popspot.app.data.di

import android.content.Context
import androidx.room.Room
import com.popspot.app.data.local.PopSpotDatabase
import com.popspot.app.data.local.ScrapDao
import com.popspot.app.data.repository.ScrapRepositoryImpl
import com.popspot.app.domain.repository.ScrapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): PopSpotDatabase =
        Room.databaseBuilder(ctx, PopSpotDatabase::class.java, "popspot.db").build()

    @Provides
    @Singleton
    fun provideScrapDao(db: PopSpotDatabase): ScrapDao = db.scrapDao()

    @Provides
    @Singleton
    fun provideScrapRepository(impl: ScrapRepositoryImpl): ScrapRepository = impl
}
