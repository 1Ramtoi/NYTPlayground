package com.example.data.features.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import javax.inject.Inject

class TopStoriesCacheImpl @Inject constructor(driver: SqlDriver): TopStoriesCache {
    private val queries = PLaygroundDB(driver).articleQueries

    override suspend fun insertArticle() {
        queries.insertItem()
    }
}