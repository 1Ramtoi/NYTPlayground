package com.example.nytplayground.common.di

import android.app.Application
import androidx.room.Room
import com.example.data.common.di.DataModule
import com.example.data.features.datasource.cache.PlaygroundDB
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
    fun provideDatabase(application: Application): PlaygroundDB {
        return Room.databaseBuilder(
            application,
            PlaygroundDB::class.java,
            "playgroundDB"
        ).build()
    }
}