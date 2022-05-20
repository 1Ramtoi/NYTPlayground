package com.example.nytplayground.features.topstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.usecases.topstories.ReqeustTopStoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopStoriesViewModel @Inject constructor(
    private val requestTopStoriesUseCase: ReqeustTopStoriesUseCase,
) : ViewModel() {

    private val _topStories = MutableStateFlow<List<Article>>(listOf())
    val topStories: StateFlow<List<Article>>
        get() = _topStories

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refreshTopStories() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            requestTopStoriesUseCase.invokeSuspend(TopStoriesSortBy.MOST_VIEWED).also {
                _topStories.emit(it)
                _isRefreshing.emit(false)
            }
        }
    }
}