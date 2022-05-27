package com.example.nytplayground.features.topstories

import com.example.domain.features.model.TopStoriesSortBy

sealed class TopStoriesEvent {
    data class SetSortBy(val sortBy: TopStoriesSortBy): TopStoriesEvent()
}