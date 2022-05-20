package com.example.data.common.di

import android.content.Context
import com.example.data.cache.PLaygroundDB
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.android.*
//import com.squareup.sqldelight.android.AndroidSqliteDriver

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

    @Provides
    @Singleton
    fun provideSqlDriver(context: Context): SqlDriver {
        return JbdcSqliteDriver
//        return AndroidSqliteDriver(PLaygroundDB.Schema, context, "playground.db")
    }
}