package com.devakashk.jetpacknewsapp.ui.news

import com.devakashk.jetpacknewsapp.domain.models.NewsArticle

sealed interface NewsState {
    data object Loading: NewsState
    data class Success(val news:List<NewsArticle>): NewsState
    data class Error(val message:String): NewsState
    data object  Empty: NewsState
}