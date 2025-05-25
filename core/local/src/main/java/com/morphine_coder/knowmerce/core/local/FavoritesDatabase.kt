package com.morphine_coder.knowmerce.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morphine_coder.knowmerce.core.local.dao.FavoritesDao
import com.morphine_coder.knowmerce.core.local.entity.FavoriteEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}