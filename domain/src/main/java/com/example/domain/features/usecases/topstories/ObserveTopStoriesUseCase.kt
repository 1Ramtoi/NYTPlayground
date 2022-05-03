package com.example.domain.features.usecases.topstories

import com.example.domain.common.interactors.FlowUseCase
import com.example.domain.features.model.Article
import com.example.domain.features.repositories.TopStoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTopStoriesUseCase @Inject constructor(
    private val topStoriesRepository: TopStoriesRepository
): FlowUseCase<List<Article>, Void?>() {
    override fun buildStream(params: Void?): Flow<List<Article>> {
        return topStoriesRepository.observeTopStories()
    }
}