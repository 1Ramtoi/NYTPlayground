package com.example.domain.features.usecases.search

import com.example.domain.common.interactors.CoroutineSingleUseCase
import com.example.domain.features.model.Article
import com.example.domain.features.repositories.SearchRepository
import com.example.domain.features.repositories.TopStoriesRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
): CoroutineSingleUseCase<String, Void?>() {
    override suspend fun execute(params: Void?): String {
//        searchRepository.search()
        return "placeholder"
    }
}