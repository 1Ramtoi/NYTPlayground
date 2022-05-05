package com.example.data.features.repositories

import com.example.data.features.datasource.remote.topstories.TopStoriesService
import com.example.domain.features.model.Article
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val topStoriesService: TopStoriesService
): TopStoriesRepository {
    override suspend fun requestMostViewed(): List<Article> {
        return topStoriesService.fetchTopViewed()
    }

    override suspend fun requestMostEmailed(): List<Article> {
        return topStoriesService.fetchTopEmailed()
    }

    override suspend fun requestMostShared(): List<Article> {
        return topStoriesService.fetchTopShared()
    }

    override fun observeMostViewed(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}