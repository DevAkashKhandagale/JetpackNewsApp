package com.devakashk.mynewsapp.ui.screens.news

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.devakashk.mynewsapp.models.HomeUiState
import com.devakashk.mynewsapp.models.NewsItem
import com.devakashk.mynewsapp.utils.formattedDate

@Composable
fun NewsList(data: List<NewsItem>, onItemClicked: (NewsItem) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.systemBars),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data) {
            Log.d("Images", "NewsList: ${it.imageUrl}")
            NewsCard(item = it, onItemClicked = onItemClicked)
        }
    }
}


@Composable
fun NewsListScreen(viewModel: NewsViewModel, onItemClicked: (NewsItem) -> Unit) {

    val homeState = viewModel.uiState.collectAsStateWithLifecycle()

    if (homeState.value.isEmpty()){
        ShowLoading()
    }else{
        NewsList(data = homeState.value, onItemClicked =onItemClicked)
    }
}

@Composable
fun NewsCard(item: NewsItem, onItemClicked: (NewsItem) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = {
            onItemClicked(item)
        }
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(80.dp)
                    .padding(8.dp)
            )
            Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.Start) {
                Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 18.sp, maxLines = 1, modifier = Modifier.padding(2.dp))
                Text(text = item.description, modifier = Modifier.padding(2.dp), fontSize = 16.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(text = item.publishedDate.formattedDate(), modifier = Modifier.padding(2.dp), fontSize = 12.sp)
            }

            Icon(imageVector = Icons.Default.PlayArrow, contentDescription ="Go to next page" )
        }
    }
}

@Composable
fun ShowLoading(){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }

}