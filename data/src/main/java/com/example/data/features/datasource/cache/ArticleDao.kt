package com.example.data.features.datasource.cache

import androidx.room.Dao
import androidx.room.Query
import com.example.data.common.cache.BaseCoroutinesDao
import com.example.data.common.model.LocalArticle
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticleDao: BaseCoroutinesDao<LocalArticle>() {

    @Query("SELECT * FROM articles")
    abstract suspend fun requestAllArticles(): List<LocalArticle>

    @Query("SELECT * FROM articles")
    abstract fun getAllArticlesFlow(): Flow<List<LocalArticle>>
}