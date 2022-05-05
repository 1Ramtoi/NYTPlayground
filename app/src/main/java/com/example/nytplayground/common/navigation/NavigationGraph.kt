package com.example.nytplayground.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nytplayground.features.search.SearchView
import com.example.nytplayground.features.search.SearchViewModel
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
            TopStoriesView(vm = topStoriesViewModel, onSearch = {
                navController.navigate(Screen.Search.route)
            })
        }
        composable(Screen.Search.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchView(vm = searchViewModel)
        }

    }
}