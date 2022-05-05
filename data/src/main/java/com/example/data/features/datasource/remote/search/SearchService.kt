package com.example.data.features.datasource.remote.search

import com.example.data.features.datasource.remote.BaseService

abstract class SearchService: BaseService() {

    abstract suspend fun fetchResults()
}