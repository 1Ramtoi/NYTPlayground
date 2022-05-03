package com.example.data.common.di

import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }
}