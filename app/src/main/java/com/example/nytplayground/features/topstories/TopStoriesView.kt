package com.example.nytplayground.features.topstories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nytplayground.common.articles.ArticleCompactView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun TopStoriesView(
    vm: TopStoriesViewModel,
    onSearch: () -> Unit
) {
    println("TopStoriesView")

    LaunchedEffect(Unit, block = {
        vm.refreshTopStories()
    })

    val isRefreshing by vm.isRefreshing.collectAsState()
    val topStories by vm.topStories.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Top Stories")
                },
                actions = {
                    IconButton(onClick = { onSearch() }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        },
        content = {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { vm.refreshTopStories() }) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(topStories) { article ->
                            Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                                ArticleCompactView(article = article)
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    )
}