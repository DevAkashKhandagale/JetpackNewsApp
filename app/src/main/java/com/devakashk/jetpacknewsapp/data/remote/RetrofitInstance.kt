package com.devakashk.jetpacknewsapp.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const  val BASE_URL="https://saurav.tech/NewsAPI/"

    private val gson= GsonBuilder().setLenient().create()


    private val client= OkHttpClient.Builder().connectTimeout(30,TimeUnit.SECONDS).readTimeout(30,
        TimeUnit.SECONDS).build()

    val newsApi: NewsApi by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsApi::class.java)
    }

}