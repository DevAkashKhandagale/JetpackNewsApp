package com.devakashk.jetpacknewsapp.domain.models

data class NewsArticle(
    val id: String = "",
    val title: String = "",
    val description: String? = "",
    val url: String = "",
    val urlToImage: String? = null,
    val publishedAt: String = "",
    val source: String = "",
    val content: String? = null
)