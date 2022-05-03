package com.example.data.features.datasource.remote

import com.example.data.features.datasource.remote.model.Article
import com.example.data.features.datasource.remote.model.ArticleRaw
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject


class TopStoriesServiceImpl @Inject constructor(): TopStoriesService() {
    override suspend fun fetchTopStores(): List<Article> {
        Client.http.use {
            return it.get(HOST + TOP_STORIES)
        }
    }

    override suspend fun fetchTopEmailed(): List<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTopShared(): List<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchTopViewed(): List<Article> {
        TODO("Not yet implemented")
    }
}