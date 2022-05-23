package com.example.nytplayground.features.topstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.usecases.topstories.ObserveTopStoriesUseCase
import com.example.domain.features.usecases.topstories.RefreshTopStoriesUseCase
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
    private val observeTopStoriesUseCase: ObserveTopStoriesUseCase,
    private val refreshTopStoriesUseCase: RefreshTopStoriesUseCase
) : ViewModel() {

    private val _mostViewed = MutableStateFlow<List<Article>>(listOf())
    val mostViewed: StateFlow<List<Article>>
        get() = _mostViewed

    private val _mostShared = MutableStateFlow<List<Article>>(listOf())
    val mostShared: StateFlow<List<Article>>
        get() = _mostShared

    private val _mostEmailed = MutableStateFlow<List<Article>>(listOf())
    val mostEmailed: StateFlow<List<Article>>
        get() = _mostEmailed


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_VIEWED).collect {
                _mostViewed.emit(it)
            }
            observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_SHARED).collect {
                _mostShared.emit(it)
            }
            observeTopStoriesUseCase.buildStream(TopStoriesSortBy.MOST_EMAILED).collect {
                _mostEmailed.emit(it)
            }
        }
    }

    fun refreshMostViewed() {
        requestTopStories(TopStoriesSortBy.MOST_VIEWED, _mostViewed)
//        refreshTopStories(TopStoriesSortBy.MOST_VIEWED)
    }

    fun refreshMostShared() {
        requestTopStories(TopStoriesSortBy.MOST_SHARED, _mostShared)
//        refreshTopStories(TopStoriesSortBy.MOST_SHARED)
    }

    fun refreshMostEmailed() {
        requestTopStories(TopStoriesSortBy.MOST_EMAILED, _mostEmailed)
//        refreshTopStories(TopStoriesSortBy.MOST_EMAILED)
    }

    private fun requestTopStories(
        sortBy: TopStoriesSortBy,
        observedList: MutableStateFlow<List<Article>>
    ) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            requestTopStoriesUseCase.invokeSuspend(sortBy).also {
                observedList.emit(it) //this has to go
                _isRefreshing.emit(false)
            }
        }
    }

    private fun refreshTopStories(sortBy: TopStoriesSortBy) {
        viewModelScope.launch {
            refreshTopStoriesUseCase.invokeSuspend(sortBy)
        }
    }
}