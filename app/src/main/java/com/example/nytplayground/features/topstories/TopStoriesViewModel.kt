package com.example.nytplayground.features.topstories

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.model.Article
import com.example.domain.features.usecases.topstories.ReqeustTopStoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopStoriesViewModel @Inject constructor(
    private val requestTopStoriesUseCase: ReqeustTopStoriesUseCase,
//    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _topStories = mutableStateListOf<Article>()
    val topStories: List<Article>
        get() = _topStories

    suspend fun getTopStories() {
        viewModelScope.launch {
            _topStories.addAll(requestTopStoriesUseCase.invokeSuspend(null))
        }
    }
}