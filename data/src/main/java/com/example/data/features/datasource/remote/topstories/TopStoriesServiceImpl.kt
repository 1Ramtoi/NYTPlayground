package com.example.data.features.datasource.remote.topstories

import com.example.data.features.datasource.remote.model.ArticleRaw
import com.example.data.features.datasource.remote.model.NYTResponse
import com.example.data.features.datasource.remote.model.toArticle
import com.example.domain.features.model.Article
import io.ktor.client.request.*
import javax.inject.Inject


class TopStoriesServiceImpl @Inject constructor() : TopStoriesService() {
    override suspend fun fetchTopViewed(period: Int?): List<Article> {
        val response: NYTResponse = Client.http.get(buildUrl(TOP_VIEWED, period))
        return mapToArticle(response.results)
    }

    override suspend fun fetchTopEmailed(period: Int?): List<Article> {
        val response: NYTResponse = Client.http.get(buildUrl(TOP_EMAILED, period))
        return mapToArticle(response.results)
    }

    override suspend fun fetchTopShared(period: Int?): List<Article> {
        val response: NYTResponse = Client.http.get(buildUrl(TOP_SHARED, period))
        return mapToArticle(response.results)    }

    private fun buildUrl(url: String, period: Int?): String {
        return "$url$period.json"
    }

    private fun mapToArticle(articles: List<ArticleRaw>?): List<Article> {
        return if (articles.isNullOrEmpty()) {
            listOf()
        } else {
            articles.map {
                it.toArticle()
            }
        }
    }
}