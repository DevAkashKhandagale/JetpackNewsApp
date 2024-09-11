package com.devakashk.mynewsapp.utils
import com.devakashk.mynewsapp.models.NewsItem
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Locale


fun String.formattedDate(): String {
    val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())

    val date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}

fun String.toNewsItem(): NewsItem {
    val gson = Gson()
    return gson.fromJson(this, NewsItem::class.java)
}

fun NewsItem.toJsonString(): String {
    val gson = Gson()
    return gson.toJson(this)
}