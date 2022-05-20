package com.example.nytplayground.common.di

import com.example.data.features.datasource.cache.ArticleDao
import com.example.data.features.datasource.cache.PlaygroundDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DaoModule {


    @Provides
    @Singleton
    fun provideArticleDao(db: PlaygroundDB): ArticleDao = db.articleDao()
}