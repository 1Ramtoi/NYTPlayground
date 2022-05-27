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
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val topStoriesService: TopStoriesService,
    private val topStoriesDao: ArticleDao
) : TopStoriesRepository {
    override suspend fun requestMostViewed(): List<Article> {
        val refresh = topStoriesService.fetchTopViewed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))
        println("LIST WITH ${topStoriesDao.requestAllArticles().size} ITEMS")
        return refresh
    }

    override suspend fun requestMostEmailed(): List<Article> {
        val refresh = topStoriesService.fetchTopEmailed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))
        println("LIST WITH ${topStoriesDao.requestAllArticles().size} ITEMS")

        return refresh
    }

    override suspend fun requestMostShared(): List<Article> {
        val refresh = topStoriesService.fetchTopShared()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))
        println("LIST WITH ${topStoriesDao.requestAllArticles().size} ITEMS")

        return refresh
    }

    // cannot move this logic to usecase without accessing data:LocalArticle from the domain module
    override fun observeMostViewed(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map { list ->
            list.filter {
                it.orderViewed != null
            }.mapToArticles()
        }

    override fun observeMostShared(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map { list ->
            list.filter {
                it.orderShared != null
            }.mapToArticles()
        }

    override fun observeMostEmailed(): Flow<List<Article>> =
        topStoriesDao.getAllArticlesFlow().map { list ->
            list.filter {
                it.orderEmailed != null
            }.mapToArticles()
        }

    override suspend fun refreshMostViewed() {
        val refresh = topStoriesService.fetchTopViewed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_VIEWED))
    }

    override suspend fun refreshMostShared() {
        val refresh = topStoriesService.fetchTopShared()

        val currentList = topStoriesDao.requestAllArticles()

        //TODO
//        refresh.forEach { refreshArticle ->
//            currentList.find { localArticle ->
//                localArticle.id == refreshArticle.id
//            }?.let { localArticle ->
//                localArticle.orderShared?.let {
//                    refreshArticle
//                }
//
//            }
//
//        }

        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_SHARED))

    }

    override suspend fun refreshMostEmailed() {
        val refresh = topStoriesService.fetchTopEmailed()
        topStoriesDao.insertAllReplace(refresh.mapToLocalArticles(TopStoriesSortBy.MOST_EMAILED))
    }
}