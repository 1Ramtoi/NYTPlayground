package com.example.data.features.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class NYTResponse(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<ArticleRaw>?
)
