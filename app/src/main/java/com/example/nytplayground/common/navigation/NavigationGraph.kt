package com.example.nytplayground.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nytplayground.features.homescreen.HomescreenView
import com.example.nytplayground.features.topstories.TopStoriesView
import com.example.nytplayground.features.topstories.TopStoriesViewModel

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Homescreen.route
    ) {

        composable(Screen.Homescreen.route) {
            HomescreenView(
                navigate = {
                    navController.navigate(it)
                }
            )
        }

        composable(Screen.TopStories.route) {
            val topStoriesViewModel: TopStoriesViewModel = hiltViewModel()
            TopStoriesView(vm = topStoriesViewModel, onSearch = {
                navController.navigate(Screen.Search.route)
            })
        }
    }
}