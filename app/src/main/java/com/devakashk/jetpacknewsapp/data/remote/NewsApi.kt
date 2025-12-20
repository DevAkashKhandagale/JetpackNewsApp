package com.devakashk.jetpacknewsapp.data.remote

import com.devakashk.jetpacknewsapp.data.model.NewsResponse
import retrofit2.http.GET


interface NewsApi {
    @GET("top-headlines/category/health/in.json")
    suspend fun getNews(): NewsResponse
}