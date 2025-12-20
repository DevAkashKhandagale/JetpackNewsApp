package com.devakashk.jetpacknewsapp.data.model
// data/model/NewsResponse.kt
import com.devakashk.jetpacknewsapp.domain.models.NewsArticle
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleDto>
)

data class ArticleDto(
    @SerializedName("source") val source: SourceDto,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("content") val content: String?
) {
    fun toNewsArticle(): NewsArticle {
        return NewsArticle(
            id = "$title-$publishedAt",
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            source = source.name,
            content = content
        )
    }
}

data class SourceDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)