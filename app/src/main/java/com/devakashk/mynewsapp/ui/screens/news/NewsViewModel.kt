package com.devakashk.mynewsapp.ui.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devakashk.mynewsapp.models.HomeUiState
import com.devakashk.mynewsapp.models.NewsItem
import com.devakashk.mynewsapp.network.NewsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(var apiService: NewsApi) : ViewModel() {



    private val _uiState = MutableStateFlow<List<NewsItem>>(emptyList())
    val uiState: StateFlow<List<NewsItem>> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
            viewModelScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        val response = apiService.getNewsData()
                        _uiState.update {
                            parseNYTimesFeed(response)
                        }
                    }

                } catch (_: Throwable) {

                }

        }
    }


    suspend fun parseNYTimesFeed(response: Response<ResponseBody>): List<NewsItem> {
        val inputStream = response.body()?.byteStream() ?: return emptyList()
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(inputStream, "UTF-8")

        val newsItems = mutableListOf<NewsItem>()
        var currentItem: NewsItem? = null
        var eventType = parser.eventType

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val tagName = parser.name
                    when (tagName) {
                        "item" -> {
                            currentItem = NewsItem("", "", "", "", "")
                        }
                        "title" -> {
                            if (currentItem != null) {
                                currentItem.title = parser.nextText()
                            }
                        }
                        "description" -> {
                            if (currentItem != null) {
                                currentItem.description = parser.nextText()
                            }
                        }
                        "link" -> {
                            if (currentItem != null) {
                                currentItem.link = parser.nextText()
                            }
                        }
                        "media:content" -> {
                            if (currentItem != null) {
                                val imageUrl = parser.getAttributeValue(null, "url")
                                currentItem.imageUrl = imageUrl
                            }
                        }
                        "pubDate" -> {
                            if (currentItem != null) {
                                currentItem.publishedDate = parser.nextText()
                            }
                        }
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == "item" && currentItem != null) {
                        newsItems.add(currentItem)
                        currentItem = null
                    }
                }
            }
            eventType = parser.next()
        }

        return newsItems
    }


    sealed interface UiResult<out T> {
        object Loading : UiResult<Nothing>
        data class Success<T>(val data: T) : UiResult<T>
        data class Fail(val error: Throwable) : UiResult<Nothing>
    }
}