package com.example.nytplayground.common.di

import com.example.data.common.di.DataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModule::class])
object ApplicationModule {

    @Provides
    fun provideLogger(): String {
        return "placeholder"
    }
}