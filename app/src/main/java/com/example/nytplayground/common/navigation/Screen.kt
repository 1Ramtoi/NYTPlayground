package com.example.nytplayground.common.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument>) {

    object TopStories : Screen(route = "playground/topstories", navArguments = listOf())
}
