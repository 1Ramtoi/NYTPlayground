package com.example.nytplayground.features.topstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.model.TopStoriesSortBy.*
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
    private val observeTopStoriesUseCase: ObserveTopStoriesUseCase,
    private val refreshTopStoriesUseCase: RefreshTopStoriesUseCase
) : BaseViewModel<TopStoriesState, TopStoriesEvent>(TopStoriesState()) {

    init {
        // this cannot be the optimal way of implementing this, no?
        observeTopStoriesUseCase.executeWithViewState(MOST_VIEWED) { articleList ->
            if (sortBy == MOST_VIEWED) {
                copy(listToDisplay = articleList)
            } else {
                copy()
            }
        }
        observeTopStoriesUseCase.executeWithViewState(MOST_SHARED) { articleList ->
            if (sortBy == MOST_SHARED) {
                copy(listToDisplay = articleList)
            } else {
                copy()
            }
        }
        observeTopStoriesUseCase.executeWithViewState(MOST_EMAILED) { articleList ->
            if (sortBy == MOST_EMAILED) {
                copy(listToDisplay = articleList)
            } else {
                copy()
            }
        }

        refresh()
    }

    fun updateSortBy(sortBy: TopStoriesSortBy) {
        state.value.sortBy = sortBy
        updateList(sortBy)
    }

    private fun updateList(sortBy: TopStoriesSortBy) {
        // is this the way?
        when (sortBy) {
            MOST_VIEWED -> {
                observeTopStoriesUseCase.executeWithViewState(MOST_VIEWED) { articleList ->
                    copy(listToDisplay = articleList)
                }
            }
            MOST_SHARED -> {
                observeTopStoriesUseCase.executeWithViewState(MOST_SHARED) { articleList ->
                    copy(listToDisplay = articleList)
                }
            }
            MOST_EMAILED -> {
                observeTopStoriesUseCase.executeWithViewState(MOST_EMAILED) { articleList ->
                    copy(listToDisplay = articleList)
                }
            }
        }
    }

    fun refresh() {
        // is this the correct way of accessing the state's value?
        refreshTopStories(state.value.sortBy)
    }

    private fun refreshTopStories(sortBy: TopStoriesSortBy) {
        viewModelScope.launch {
            refreshTopStoriesUseCase.invokeSuspend(sortBy)
        }
    }
}