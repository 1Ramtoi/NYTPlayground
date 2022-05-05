package com.example.data.features.datasource.remote.search

import com.example.data.features.datasource.remote.model.NYTResponse
import io.ktor.client.request.*
import javax.inject.Inject

class SearchServiceImpl @Inject constructor(): SearchService() {
    override suspend fun fetchResults() {
        println("searching ....")
        val response: NYTResponse = Client.http.use {
            it.get('1' + ".json")
        }
    }
}