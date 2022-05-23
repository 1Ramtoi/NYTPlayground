package com.example.domain.features.usecases.topstories

import com.example.domain.common.interactors.FlowUseCase
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: TopStoriesRepository
): FlowUseCase<List<Article>, TopStoriesSortBy>() {
    override fun buildStream(params: TopStoriesSortBy?): Flow<List<Article>> {
        return when(params) {
            TopStoriesSortBy.MOST_VIEWED -> topStoriesRepository.observeMostViewed()
            TopStoriesSortBy.MOST_SHARED -> topStoriesRepository.observeMostShared()
            TopStoriesSortBy.MOST_EMAILED -> topStoriesRepository.observeMostEmailed()
            null -> topStoriesRepository.observeMostViewed()
        }
    }
}