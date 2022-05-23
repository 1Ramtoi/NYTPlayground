package com.example.data.features.repositories

import android.util.Log
import com.example.data.common.model.mapToArticles
import com.example.data.common.model.mapToLocalArticles
import com.example.data.common.model.toArticle
import com.example.data.features.datasource.cache.ArticleDao
import com.example.data.features.datasource.remote.topstories.TopStoriesService
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformLatest
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val topStoriesService: TopStoriesService,
    private val topStoriesDao: ArticleDao
) : TopStoriesRepository {
    override suspend fun requestMostViewed(): List<Article> {
        val refresh = topStoriesService.fetchTopViewed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))

        return refresh
    }

    override suspend fun requestMostEmailed(): List<Article> {
        val refresh = topStoriesService.fetchTopEmailed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))

        return refresh
    }

    override suspend fun requestMostShared(): List<Article> {
        val refresh = topStoriesService.fetchTopShared()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))

        return refresh
    }

    // is this logic supposed to be here?
    // SQL query vs. Kotlin filter ?
    override fun observeMostViewed(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map {
            it.filter {
                it.orderViewed != null
            }.mapToArticles()
        }

    override fun observeMostShared(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map {
            it.filter {
                it.orderShared != null
            }.mapToArticles()
        }

    override fun observeMostEmailed(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map {
            it.filter {
                it.orderEmailed != null
            }.mapToArticles()
        }

    override suspend fun refreshMostViewed() {
        val refresh = topStoriesService.fetchTopViewed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))
    }

    override suspend fun refreshMostShared() {
        val refresh = topStoriesService.fetchTopShared()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))

    }

    override suspend fun refreshMostEmailed() {
        val refresh = topStoriesService.fetchTopEmailed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))
    }
}