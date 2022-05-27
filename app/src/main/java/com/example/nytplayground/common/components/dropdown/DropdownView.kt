package com.example.nytplayground.common.components.dropdown

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.features.model.TopStoriesSortBy

@Composable
fun TopBarDropdownFilter(
    sortBy: TopStoriesSortBy,
    setSortBy: (TopStoriesSortBy) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)

    ) {
        IconButton(onClick = {
            expanded.value = true
        }) {
            Icon(imageVector = Icons.Default.Sort, contentDescription = "Filter")
        }
    }

    DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
        DropdownMenuItem(onClick = {
            setSortBy(TopStoriesSortBy.MOST_VIEWED) }) {
            Text(text = "most viewed")
        }

        Divider()

        DropdownMenuItem(onClick = { setSortBy(TopStoriesSortBy.MOST_SHARED) }) {
            Text(text = "most shared")
        }

        Divider()

        DropdownMenuItem(onClick = { setSortBy(TopStoriesSortBy.MOST_EMAILED) }) {
            Text(text = "most emailed")
        }
    }
}