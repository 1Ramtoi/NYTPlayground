package com.example.nytplayground.common.di

import android.util.Log
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideLogger(): String {
        return "placeholder"
    }
}