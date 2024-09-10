package com.devakashk.mynewsapp.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml")
    suspend fun getNewsData():Response<ResponseBody>
}