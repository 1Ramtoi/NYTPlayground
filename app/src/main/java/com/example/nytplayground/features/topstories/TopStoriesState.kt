package com.example.nytplayground.features.topstories

import com.example.domain.features.model.Article

data class TopStoriesState(
    // what belongs here?
    val mostViewed: List<Article> = listOf(),
    val mostShared: List<Article> = listOf(),
    val mostEmailed: List<Article> = listOf()
)