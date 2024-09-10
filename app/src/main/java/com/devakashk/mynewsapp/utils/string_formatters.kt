package com.devakashk.mynewsapp.utils
import com.devakashk.mynewsapp.models.NewsItem
import com.google.gson.Gson


fun String.formattedDate(): String {
    if(this.isEmpty()){
        return  "No date found"
    }
    return this.substring(0, 16)
}

fun String.toNewsItem(): NewsItem {
    val gson = Gson()
    return gson.fromJson(this, NewsItem::class.java)
}