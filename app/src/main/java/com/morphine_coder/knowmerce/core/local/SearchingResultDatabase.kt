package com.morphine_coder.knowmerce.core.local

import androidx.room.Database
import com.morphine_coder.knowmerce.core.local.dao.SearchingDao
import com.morphine_coder.knowmerce.core.local.entity.SearchingEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Database(
    entities = [SearchingEntity::class],
    version = 1
)
abstract class SearchingResultDatabase {

    abstract fun searchingDao(): SearchingDao
}