package com.example.domain.features.usecases.topstories

import com.example.domain.common.interactors.CoroutineSingleUseCase
import com.example.domain.features.repositories.TopStoriesRepository
import javax.inject.Inject

class ReqeustTopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: TopStoriesRepository
): CoroutineSingleUseCase<List<Article>, Void?>() {
    override suspend fun execute(params: Void?): List<Article> {
        return topStoriesRepository.requestTopStories()
    }
}