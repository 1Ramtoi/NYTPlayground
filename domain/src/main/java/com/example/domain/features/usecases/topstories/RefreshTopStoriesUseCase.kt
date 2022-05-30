package com.example.domain.features.usecases.topstories

import com.example.domain.common.interactors.CoroutineCompletableUseCase
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import javax.inject.Inject

class RefreshTopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: TopStoriesRepository
) : CoroutineCompletableUseCase<TopStoriesSortBy>() {
    override suspend fun execute(params: TopStoriesSortBy?) {
        when (params) {
            TopStoriesSortBy.MOST_VIEWED -> topStoriesRepository.refreshMostViewed()
            TopStoriesSortBy.MOST_SHARED -> topStoriesRepository.refreshMostShared()
            TopStoriesSortBy.MOST_EMAILED -> topStoriesRepository.refreshMostEmailed()
            null -> topStoriesRepository.refreshMostViewed()
        }
    }
}