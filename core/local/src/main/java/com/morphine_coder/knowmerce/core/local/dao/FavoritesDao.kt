package com.morphine_coder.knowmerce.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.morphine_coder.knowmerce.core.local.entity.FavoriteEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Dao
interface FavoritesDao {

    @Insert
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)


}