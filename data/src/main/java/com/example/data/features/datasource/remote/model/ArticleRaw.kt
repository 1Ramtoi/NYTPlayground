package com.example.data.features.datasource.remote.model

import com.example.domain.features.model.Article
import kotlinx.serialization.Serializable


@Serializable
data class ArticleRaw(
    val url: String?,
    val adx_keywords: String?,
    val subsection: String?,
    val column: String?,
    val eta_id: Int?,
    val section: String?,
    val id: String?,
    val asset_id: String?,
    val nytdsection: String?,
    val byline: String?,
    val type: String?,
    val title: String?,
    val abstract: String?,
    val published_date: String?,
    val source: String?,
    val updated: String?
)

fun ArticleRaw.toArticle(): Article {
    return Article(
        id = id!!,
        title = title!!,
        abstract = abstract!!,
        published_date = published_date!!
    )
}
