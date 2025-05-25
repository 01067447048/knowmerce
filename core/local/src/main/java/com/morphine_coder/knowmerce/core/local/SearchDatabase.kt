package com.morphine_coder.knowmerce.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morphine_coder.knowmerce.core.local.dao.SearchDao
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Database(
    entities = [SearchResultEntity::class],
    version = 1
)
abstract class SearchDatabase: RoomDatabase() {

    abstract fun searchDao(): SearchDao
}