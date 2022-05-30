package com.example.nytplayground.common.components.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.domain.features.model.Article

@Composable
fun ArticleDetailedView(
    article: Article
) {
    Text(text = article.title)
    //TODO
}