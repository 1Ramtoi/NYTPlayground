package com.example.data.common.di

import com.example.data.features.datasource.cache.TopStoriesCache
import com.example.data.features.datasource.cache.TopStoriesCacheImpl
import com.example.data.features.datasource.remote.search.SearchService
import com.example.data.features.datasource.remote.search.SearchServiceImpl
import com.example.data.features.datasource.remote.topstories.TopStoriesService
import com.example.data.features.datasource.remote.topstories.TopStoriesServiceImpl
import com.example.data.features.repositories.SearchRepositoryImpl
import com.example.data.features.repositories.TopStoriesRepositoryImpl
import com.example.domain.features.repositories.SearchRepository
import com.example.domain.features.repositories.TopStoriesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModuleBinds {

    @Binds
    @Singleton
    fun bindTopStoriesRepository(repo: TopStoriesRepositoryImpl): TopStoriesRepository

    @Binds
    @Singleton
    fun bindTopStoriesService(service: TopStoriesServiceImpl): TopStoriesService

    @Binds
    @Singleton
    fun bindTopStoriesCache(cache: TopStoriesCacheImpl): TopStoriesCache

    @Binds
    @Singleton
    fun bindSearchRepository(repo: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    fun bindSearchService(service: SearchServiceImpl): SearchService

}