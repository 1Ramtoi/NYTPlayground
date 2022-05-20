package com.example.data.common.cache

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseCoroutinesDao<in E> {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    abstract suspend fun insertReplace(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE) // or OnConflictStrategy.REPLACE
    abstract suspend fun insertIgnore(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    abstract suspend fun insertAllReplace(entities: List<E>)

    @Insert(onConflict = OnConflictStrategy.IGNORE) // or OnConflictStrategy.REPLACE
    abstract suspend fun insertAllIgnore(entities: List<E>)

    @Update
    abstract suspend fun updateSuspend(entity: E)

    @Delete
    abstract suspend fun deleteSuspend(entity: E): Int
}