package com.example.data.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy

@Entity(tableName = "articles")
data class LocalArticle(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "abstract")
    var abstract: String = "",

    @ColumnInfo(name = "published_date")
    var published_date: String = "",

    var orderViewed: Int? = null,
    var orderShared: Int? = null,
    var orderEmailed: Int? = null,
)

fun LocalArticle.toArticle() = Article(
    id = id,
    published_date = published_date,
    abstract = abstract,
    title = title
)

fun List<LocalArticle>.mapToArticles(): List<Article> = map {
    it.toArticle()
}

fun Article.toLocalArticle(sortBy: TopStoriesSortBy? = null, index: Int? = null): LocalArticle {
    val localArticle = LocalArticle(
        id = id,
        title = title,
        abstract = abstract,
        published_date = published_date,
    )

    when(sortBy) {
        TopStoriesSortBy.MOST_VIEWED -> localArticle.orderViewed = index
        TopStoriesSortBy.MOST_EMAILED -> localArticle.orderEmailed = index
        TopStoriesSortBy.MOST_SHARED -> localArticle.orderShared = index
        else -> {}
    }

    return localArticle
}

fun List<Article>.mapToLocalArticles(sortBy: TopStoriesSortBy? = null): List<LocalArticle> =
    mapIndexed { index, article ->
        article.toLocalArticle(sortBy, index)
    }