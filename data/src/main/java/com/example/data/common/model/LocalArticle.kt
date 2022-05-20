package com.example.data.common.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.features.model.Article

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
    var published_date: String = ""
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

fun Article.toLocalArticle() = LocalArticle(
    id = id,
    title = title,
    abstract = abstract,
    published_date = published_date
)

fun List<Article>.mapToLocalArticles(): List<LocalArticle> = map {
    it.toLocalArticle()
}