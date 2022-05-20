package com.example.nytplayground.common.di

import android.app.Application
import android.content.Context
import com.example.data.cache.PLaygroundDB
import com.example.data.common.di.DataModule
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModule::class])
object ApplicationModule {

    @Provides
    fun provideLogger(): String {
        return "placeholder"
    }

    @Provides
    @Singleton
    fun provideSqlDriver(application: Application): SqlDriver {
        return AndroidSqliteDriver(PLaygroundDB.Schema, application.applicationContext, "playground.db")
    }
}