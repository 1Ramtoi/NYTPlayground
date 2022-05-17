package com.example.nytplayground.features.homescreen

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomescreenFeatureCard(
    name: String,
    navigate: () -> Unit
) {
    Card(
        onClick = navigate,
        backgroundColor = MaterialTheme.colors.secondary,
        shape = MaterialTheme.shapes.large,
        elevation = 3.dp,
        modifier = Modifier.defaultMinSize(minWidth = 120.dp, minHeight = 120.dp)
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = name,
            modifier = Modifier.padding(10.dp)
        )
    }
}