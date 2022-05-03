package com.example.data.features.repositories

import com.example.data.features.datasource.remote.TopStoriesService
import com.example.domain.features.model.Article
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val topStoriesService: TopStoriesService
): TopStoriesRepository {
    override suspend fun requestTopStories(): List<Article> {
        return topStoriesService.fetchTopStores()
    }

    override fun observeTopStories(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}