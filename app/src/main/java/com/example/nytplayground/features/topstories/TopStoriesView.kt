package com.example.nytplayground.features.topstories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.nytplayground.common.components.articles.ArticleCompactView
import com.example.nytplayground.common.components.dropdown.TopBarDropdownFilter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun TopStoriesView(
    vm: TopStoriesViewModel,
    onSearch: () -> Unit
) {
    // is this supposed to be here or in the viewmodel?
    val sortBy = remember { mutableStateOf(TopStoriesSortBy.MOST_VIEWED) }

    val isRefreshing by vm.isRefreshing.collectAsState()
    val mostViewedList by vm.mostViewed.collectAsState()
    val mostSharedList by vm.mostShared.collectAsState()
    val mostEmailedList by vm.mostEmailed.collectAsState()

    fun listToDisplay(): List<Article> {
        return when (sortBy.value) {
            TopStoriesSortBy.MOST_VIEWED -> mostViewedList
            TopStoriesSortBy.MOST_SHARED -> mostSharedList
            TopStoriesSortBy.MOST_EMAILED -> mostEmailedList
            else -> {
                mostViewedList
            }
        }
    }

    fun refresh() {
        when (sortBy.value) {
            TopStoriesSortBy.MOST_VIEWED -> vm.refreshMostViewed()
            TopStoriesSortBy.MOST_SHARED -> vm.refreshMostShared()
            TopStoriesSortBy.MOST_EMAILED -> vm.refreshMostEmailed()
            else -> {
                vm.refreshMostViewed()
            }
        }
    }

    fun refreshIfEmpty() {
        if (listToDisplay().isEmpty()) {
            refresh()
        }
    }

    LaunchedEffect(Unit, block = {
        refreshIfEmpty()
    })

    LaunchedEffect(key1 = listToDisplay(), block = {
        refreshIfEmpty()
    })

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
                    TopBarDropdownFilter(sortBy = sortBy)
                }
            )
        },
        content = {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { refresh() }) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(listToDisplay()) { article ->
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