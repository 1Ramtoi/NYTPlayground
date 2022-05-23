package com.example.nytplayground.features.topstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.usecases.topstories.ObserveTopStoriesUseCase
import com.example.domain.features.usecases.topstories.RefreshTopStoriesUseCase
import com.example.domain.features.usecases.topstories.ReqeustTopStoriesUseCase
import com.example.nytplayground.common.viewmodel.BaseViewModel
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
) : BaseViewModel<TopStoriesState, TopStoriesEvent>(TopStoriesState()) {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    init {
        observeTopStoriesUseCase.executeWithViewState(TopStoriesSortBy.MOST_VIEWED) { articleList ->
            copy(mostViewed = articleList)
        }
        observeTopStoriesUseCase.executeWithViewState(TopStoriesSortBy.MOST_SHARED) { articleList ->
            copy(mostShared = articleList)
        }
        observeTopStoriesUseCase.executeWithViewState(TopStoriesSortBy.MOST_EMAILED) { articleList ->
            copy(mostEmailed = articleList)
        }
    }

    fun refreshMostViewed() {
        refreshTopStories(TopStoriesSortBy.MOST_VIEWED)
    }

    fun refreshMostShared() {
        refreshTopStories(TopStoriesSortBy.MOST_SHARED)
    }

    fun refreshMostEmailed() {
        refreshTopStories(TopStoriesSortBy.MOST_EMAILED)
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