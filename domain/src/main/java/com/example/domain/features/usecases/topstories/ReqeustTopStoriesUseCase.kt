package com.example.domain.features.usecases.topstories

import com.example.domain.common.interactors.CoroutineSingleUseCase
import com.example.domain.features.model.Article
import com.example.domain.features.model.TopStoriesSortBy
import com.example.domain.features.repositories.TopStoriesRepository
import javax.inject.Inject

class ReqeustTopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: TopStoriesRepository,
): CoroutineSingleUseCase<List<Article>, TopStoriesSortBy>() {
    override suspend fun execute(params: TopStoriesSortBy?): List<Article> {
        return when(params) {
            TopStoriesSortBy.MOST_VIEWED -> topStoriesRepository.requestMostViewed()
            TopStoriesSortBy.MOST_SHARED -> topStoriesRepository.requestMostShared()
            TopStoriesSortBy.MOST_EMAILED -> topStoriesRepository.requestMostEmailed()
            null -> topStoriesRepository.requestMostViewed()
        }
    }
}