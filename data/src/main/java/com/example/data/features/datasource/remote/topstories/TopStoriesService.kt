package com.example.data.features.datasource.remote.topstories

import com.example.data.features.datasource.remote.BaseService
import com.example.domain.features.model.Article


abstract class TopStoriesService: BaseService() {
    val TOP_EMAILED = "mostpopular/v2/emailed/"
    val TOP_SHARED = "mostpopular/v2/shared/"
    val TOP_VIEWED = "mostpopular/v2/viewed/"

    abstract suspend fun fetchTopEmailed(period: Int? = 1): List<Article>

    abstract suspend fun fetchTopShared(period: Int? = 1): List<Article>

    abstract suspend fun fetchTopViewed(period: Int? = 1): List<Article>

}