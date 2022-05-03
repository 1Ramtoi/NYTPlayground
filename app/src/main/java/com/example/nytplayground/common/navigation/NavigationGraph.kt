package com.example.nytplayground.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nytplayground.common.navigation.Screen
import com.example.nytplayground.features.topstories.TopStoriesView
import com.example.nytplayground.features.topstories.TopStoriesViewModel

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.TopStories.route
    ) {
        composable(Screen.TopStories.route) {
            val topStoriesViewModel: TopStoriesViewModel = hiltViewModel<TopStoriesViewModel>()
            TopStoriesView(vm = topStoriesViewModel)
        }
    }
}