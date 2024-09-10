package com.devakashk.mynewsapp.ui.screens

import MyNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.devakashk.mynewsapp.ui.screens.news.NewsListScreen
import com.devakashk.mynewsapp.ui.screens.news.NewsViewModel
import com.devakashk.mynewsapp.ui.theme.MyNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels()


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            MyNewsAppTheme {
                Scaffold(topBar = { TopAppBar(title = { Text("My News App") }) }) { it
                    MyNavigation(newsViewModel)
                }
            }
        }
    }
}
