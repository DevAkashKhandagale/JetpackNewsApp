package com.devakashk.mynewsapp.models

data class HomeUiState(
    val title: String,
    val link: String,
    val description: String,
    val newsItems: List<NewsItem>
)

data class NewsItem(
    var title: String,
    var description: String,
    var link: String,
    var imageUrl: String,
    var publishedDate: String
) {
    override fun toString(): String {
        return "NewsItem(title='$title', description='$description', imageUrl='$imageUrl')"
    }
}