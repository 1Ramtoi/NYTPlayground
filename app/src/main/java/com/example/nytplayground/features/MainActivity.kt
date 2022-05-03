package com.example.nytplayground.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.nytplayground.common.navigation.NavigationGraph
import com.example.nytplayground.ui.theme.NYTPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Playground()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NYTPlaygroundTheme {
        NavigationGraph()
    }
}