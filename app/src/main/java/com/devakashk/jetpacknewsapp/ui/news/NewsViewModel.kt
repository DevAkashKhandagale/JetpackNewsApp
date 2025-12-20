package com.devakashk.jetpacknewsapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devakashk.jetpacknewsapp.data.repository.NewsRepository
import com.devakashk.jetpacknewsapp.domain.models.NewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {
    private val _newsState = MutableStateFlow<NewsState>(NewsState.Empty)
    val newsState: StateFlow<NewsState> = _newsState.asStateFlow()

    private val _selectedArticle=MutableStateFlow<NewsArticle?>(null)
    val selectedArticle: StateFlow<NewsArticle?> =_selectedArticle.asStateFlow()

    fun fetchNews(){
        _newsState.value= NewsState.Loading

        viewModelScope.launch {
            val result=newsRepository.getHealthNews()

            _newsState.value= when{
                result.isSuccess ->{
                    val news= result.getOrNull() ?: emptyList()
                    if(news.isEmpty()) NewsState.Empty
                    else NewsState.Success(news)
                }
                else -> NewsState.Error(result.exceptionOrNull()?.message ?: "Unknown Error Occured")
            }

        }
    }

    fun selectArticle(article: NewsArticle){
        _selectedArticle.value=article
    }

    fun clearSelection(){
        _selectedArticle.value=null
    }

    fun retry(){
        fetchNews()
    }

            
}