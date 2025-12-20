package com.devakashk.jetpacknewsapp.data.repository

import com.devakashk.jetpacknewsapp.data.remote.NewsApi
import com.devakashk.jetpacknewsapp.domain.models.NewsArticle
import javax.inject.Inject

interface NewsRepository{
    suspend fun getHealthNews(): Result<List<NewsArticle>>
}

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi): NewsRepository{

    override suspend fun getHealthNews(): Result<List<NewsArticle>> {
        return try {
            val response=newsApi.getNews()
            if(response.status=="ok"){
                Result.success(response.articles.map { it.toNewsArticle() })
            }else{
                Result.failure(Exception("Failed to fetch news"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}