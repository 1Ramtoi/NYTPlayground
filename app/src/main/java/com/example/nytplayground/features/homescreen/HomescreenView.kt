package com.example.nytplayground.features.homescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nytplayground.common.navigation.Screen
import com.example.nytplayground.ui.theme.NYTPlaygroundTheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment


@Composable
fun HomescreenView(
    navigate: (route: String) -> Unit
) {
    val featureCards = listOf(Screen.TopStories, Screen.Search)

    Scaffold(
        modifier = Modifier.fillMaxWidth()
    ) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.SpaceEvenly,
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            featureCards.forEach { screen ->
                screen.name?.let { name ->
                    HomescreenFeatureCard(name = name, navigate = {
                        navigate(screen.route)
                    })
                }
            }
        }
    }
}


// not working ??
@Preview
@Composable
fun HomescreenViewPreview() {
    NYTPlaygroundTheme {
        HomescreenView(navigate = {})
    }
}