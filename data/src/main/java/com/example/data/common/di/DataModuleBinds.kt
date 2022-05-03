package com.example.data.common.di

import com.example.data.features.datasource.remote.TopStoriesService
import com.example.data.features.datasource.remote.TopStoriesServiceImpl
import com.example.data.features.repositories.TopStoriesRepositoryImpl
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

}