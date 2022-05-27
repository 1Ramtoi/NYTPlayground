package com.example.nytplayground.features.topstories

import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy

data class TopStoriesState(
    // what belongs here?
    val listToDisplay: List<Article> = listOf(),
    var sortBy: TopStoriesSortBy = TopStoriesSortBy.MOST_VIEWED,
    var isRefreshing: Boolean = false,
)