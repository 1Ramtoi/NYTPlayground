package com.example.data.features.repositories

import android.util.Log
import com.example.data.common.model.mapToLocalArticles
import com.example.data.features.datasource.cache.ArticleDao
import com.example.data.features.datasource.remote.topstories.TopStoriesService
import com.example.domain.features.model.Article
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val topStoriesService: TopStoriesService,
    private val topStoriesDao: ArticleDao
): TopStoriesRepository {
    override suspend fun requestMostViewed(): List<Article> {
        val refresh = topStoriesService.fetchTopViewed()
        val list = refresh.mapToLocalArticles()
        topStoriesDao.insertAllReplace(list)
        Log.d("Repo", "list retrieved with size ${topStoriesDao.requestAllArticles().size}")
        return refresh
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