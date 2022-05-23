package com.example.nytplayground.common.components.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.domain.features.model.Article

@Composable
fun ArticleCompactView(
    article: Article
) {
    Text(article.title)
}