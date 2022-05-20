package com.example.data.features.repositories

import com.example.data.features.datasource.remote.search.SearchService
import com.example.domain.features.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
): SearchRepository {
    override suspend fun search() {
        searchService.fetchResults()
    }
}