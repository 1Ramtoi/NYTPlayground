package com.example.domain.features.repositories

import com.example.domain.features.model.Article
import kotlinx.coroutines.flow.Flow

interface TopStoriesRepository {
    suspend fun requestMostViewed(): List<Article>
    suspend fun requestMostEmailed(): List<Article>
    suspend fun requestMostShared(): List<Article>

    fun observeMostViewed(): Flow<List<Article>>
    fun observeMostShared(): Flow<List<Article>>
    fun observeMostEmailed(): Flow<List<Article>>

    suspend fun refreshMostViewed()
    suspend fun refreshMostShared()
    suspend fun refreshMostEmailed()
}