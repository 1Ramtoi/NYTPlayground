package com.example.data.features.datasource.remote

import com.example.data.features.datasource.remote.model.ArticleRaw

interface TopStoriesAPI {
    suspend fun getTopStories(): List<ArticleRaw>

    companion object {
        var api: TopStoriesAPI? = null
        fun getInstance(): TopStoriesAPI {
            if (api == null) {


            }
            return api!!
        }
    }
}