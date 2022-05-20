package com.example.data.features.datasource.cache

import com.example.data.cache.PLaygroundDB
import com.squareup.sqldelight.db.SqlDriver

internal fun PLaygroundDB(sqlDriver: SqlDriver) = PLaygroundDB(
    driver = sqlDriver
)