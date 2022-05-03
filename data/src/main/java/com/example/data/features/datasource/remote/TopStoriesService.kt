package com.example.data.features.datasource.remote

import com.example.domain.features.model.Article


abstract class TopStoriesService: BaseService() {
    val TOP_STORIES = ""
    val TOP_EMAILED = "mostpopular/v2/emailed/"
    val TOP_SHARED = ""
    val TOP_VIEWED = ""


    abstract suspend fun fetchTopStores(): List<Article>

    abstract suspend fun fetchTopEmailed(): List<Article>

    abstract suspend fun fetchTopShared(): List<Article>

    abstract suspend fun fetchTopViewed(): List<Article>

}