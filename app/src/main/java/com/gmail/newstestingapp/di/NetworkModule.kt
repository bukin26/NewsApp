package com.gmail.newstestingapp.di

import com.gmail.newstestingapp.model.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsService(): NewsService {
        return NewsService.create()
    }
}