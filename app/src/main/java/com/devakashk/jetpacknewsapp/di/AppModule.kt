package com.devakashk.jetpacknewsapp.di

import com.devakashk.jetpacknewsapp.data.remote.NewsApi
import com.devakashk.jetpacknewsapp.data.remote.RetrofitInstance
import com.devakashk.jetpacknewsapp.data.repository.NewsRepository
import com.devakashk.jetpacknewsapp.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi{
        return RetrofitInstance.newsApi
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository{
        return NewsRepositoryImpl(api)
    }


}