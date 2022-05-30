package com.example.nytplayground.features.topstories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.nytplayground.common.components.articles.ArticleCompactView
import com.example.nytplayground.common.components.dropdown.TopBarDropdownFilter
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun TopStoriesView(
    vm: TopStoriesViewModel,
    onSearch: () -> Unit
) {
//    val state by vm.state.collectAsState()
    val state by vm.state.collectAsState()


    fun refresh() {
        vm.refresh()
    }

    fun refreshIfEmpty() {
        if (state.listToDisplay.isEmpty()) {
            refresh()
        }
    }

    LaunchedEffect(Unit, block = {
        refreshIfEmpty()
    })

    LaunchedEffect(key1 = state.listToDisplay, block = {
        refreshIfEmpty()
    })

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    FlowRow(crossAxisAlignment = FlowCrossAxisAlignment.Center) {
                        Text("Top Stories ")
                        Text(
                            "(sorted by ${state.sortBy.toString().lowercase().replace('_', ' ')})",
                            fontSize = 14.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onSearch() }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    TopBarDropdownFilter(sortBy = state.sortBy, setSortBy = {
                        vm.updateSortBy(it)
                    })
                }
            )
        },
        content = {
            SwipeRefresh(
                state = rememberSwipeRefreshState(state.isRefreshing),
                onRefresh = { refresh() }) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(state.listToDisplay) { article ->
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