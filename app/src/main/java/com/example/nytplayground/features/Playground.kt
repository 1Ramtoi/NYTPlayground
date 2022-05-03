package com.example.nytplayground.features

import androidx.compose.runtime.Composable
import com.example.nytplayground.common.navigation.NavigationGraph
import com.example.nytplayground.ui.theme.NYTPlaygroundTheme

@Composable
fun Playground() {
    NYTPlaygroundTheme {
        NavigationGraph()
    }
}