package com.morphine_coder.knowmerce.core.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.morphine_coder.knowmerce.core.local.entity.FavoriteEntity
import com.morphine_coder.knowmerce.core.local.entity.SearchResultWithFavorite
import kotlinx.coroutines.flow.Flow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE docUrl = :docUrl)")
    suspend fun isFavorite(docUrl: String): Boolean

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Query(
        """
    SELECT sr.*, 
           CASE WHEN f.docUrl IS NOT NULL THEN 1 ELSE 0 END AS isFavorite
    FROM favorites f
    INNER JOIN searching_result sr ON f.docUrl = sr.docUrl
    ORDER BY f.addedAt DESC
"""
    )
    fun pagingFavorites(): PagingSource<Int, SearchResultWithFavorite>
}