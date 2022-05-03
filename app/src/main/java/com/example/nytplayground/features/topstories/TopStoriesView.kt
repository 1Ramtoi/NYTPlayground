package com.example.nytplayground.features.topstories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TopStoriesView(vm: TopStoriesViewModel) {
    println("TopStoriesView")

    LaunchedEffect(Unit, block = {
        vm.getTopStories()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Top Stories")
                }
            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(vm.topStories) { story ->
                        Row(modifier = Modifier.padding(16.dp)) {
                            Spacer(Modifier.width(5.dp))
                            Text(story.title)
                        }
                        Divider()
                    }
                }
            }
        }
    )
}