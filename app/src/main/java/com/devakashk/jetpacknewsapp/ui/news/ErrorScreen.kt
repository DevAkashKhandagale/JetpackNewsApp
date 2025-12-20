package com.devakashk.jetpacknewsapp.ui.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun ErrorScreen(paddingValues: PaddingValues){
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues) ){
        Text("This is error Screen", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
    }
}

@Composable
fun EmptyScreen(paddingValues: PaddingValues){
    Box(modifier = Modifier.fillMaxSize().padding(paddingValues) ){
        Text("This is empty Screen", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
    }
}