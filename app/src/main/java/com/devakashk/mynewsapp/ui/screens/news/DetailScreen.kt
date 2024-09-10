package com.devakashk.mynewsapp.ui.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devakashk.mynewsapp.models.NewsItem
import com.devakashk.mynewsapp.utils.formattedDate


@Composable
fun DetailScreen(item: NewsItem){
    Column {
        AsyncImage(model = item.imageUrl, contentDescription ="News image", modifier = Modifier.fillMaxWidth().height(250.dp).padding(8.dp))
        Text(text = item.publishedDate.formattedDate(), modifier = Modifier.padding(8.dp), fontSize = 12.sp)
        Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1, modifier = Modifier.padding(8.dp))
        Text(text = item.description, modifier = Modifier.padding(8.dp), fontSize = 16.sp)


    }
}
