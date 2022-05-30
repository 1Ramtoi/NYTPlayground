package com.example.data.features.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.common.model.LocalArticle

@Database(entities = [LocalArticle::class], version = 2)
abstract class PlaygroundDB: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}