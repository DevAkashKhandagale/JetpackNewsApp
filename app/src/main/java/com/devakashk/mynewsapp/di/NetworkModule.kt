package com.devakashk.mynewsapp.di

import com.devakashk.mynewsapp.network.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rss.nytimes.com/services/xml/rss/nyt/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesNewsService(): NewsApi {
        return providesRetrofit().create(NewsApi::class.java)
    }
}