package com.example.domain.features.repositories

import com.example.domain.features.model.Article
import kotlinx.coroutines.flow.Flow

interface TopStoriesRepository {
    suspend fun requestTopStories(): List<Article>

    fun observeTopStories(): Flow<List<Article>>
}