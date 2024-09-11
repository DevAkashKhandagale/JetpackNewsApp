package com.devakashk.mynewsapp.ui.screens.news

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.devakashk.mynewsapp.models.NewsItem
import com.devakashk.mynewsapp.utils.formattedDate


@Composable
fun DetailScreen(newsViewModel: NewsViewModel){

    val item = newsViewModel.selectedNewsItem.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        AsyncImage(model = item?.imageUrl, contentDescription ="News image", modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 36.dp, start = 8.dp, end = 8.dp), contentScale = ContentScale.FillBounds)
        Text(text = item?.publishedDate!!.formattedDate(), modifier = Modifier.padding(start = 8.dp), fontSize = 14.sp)
        Text(text = item?.title?:"No title found", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline,fontSize = 18.sp, maxLines = 1, modifier = Modifier.padding(start = 8.dp),)
        Text(text = item?.description?:"No description found", modifier = Modifier.padding(8.dp), fontSize = 16.sp)


    }
}
