package com.example.nytplayground.common.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val name: String? = null, val navArguments: List<NamedNavArgument> = listOf()) {

    object Homescreen : Screen(route = "playground/home")
    object TopStories : Screen(route = "playground/topstories", name = "Top Stories")
    object Search : Screen(route = "playground/search", name = "Search")
}
