package com.devakashk.jetpacknewsapp.ui.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val newsState = viewModel.newsState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    LaunchedEffect(newsState.value) {
        if (newsState.value is NewsState.Error) {
            val error = (newsState.value as NewsState.Error).message
            scope.launch {
                snackBarHostState.showSnackbar("Error : $error")
            }


        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Health News") }, actions = {
            IconButton(onClick = { viewModel.retry() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        })
    }, snackbarHost = { SnackbarHost(snackBarHostState) }) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (val state = newsState.value) {
                is NewsState.Loading -> {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is NewsState.Success->{
                    NewsListScreen(articles = state.news,onArticleClick=viewModel::selectArticle,
                        modifier=Modifier.fillMaxSize()
                        )
                }
                is NewsState.Error->{
                        ErrorScreen(paddingValues)
                }
                is NewsState.Empty->{
                    EmptyScreen(paddingValues)
                }
            }
        }
    }

}