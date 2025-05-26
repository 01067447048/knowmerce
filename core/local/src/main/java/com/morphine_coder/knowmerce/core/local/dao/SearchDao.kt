package com.morphine_coder.knowmerce.core.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity
import com.morphine_coder.knowmerce.core.local.entity.SearchResultWithFavorite
import kotlinx.coroutines.flow.Flow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Dao
interface SearchDao {

    @Query(
        """
    SELECT sr.*, 
           CASE WHEN f.docUrl IS NOT NULL THEN 1 ELSE 0 END as isFavorite
    FROM searching_result sr
    INNER JOIN favorites f ON sr.docUrl = f.docUrl
    ORDER BY sr.timestamp DESC
    """
    )
    fun getAllFavorites(): Flow<List<SearchResultWithFavorite>>

    @Query("SELECT * FROM searching_result WHERE keyword = :keyword ORDER BY timestamp DESC")
    fun pagingSource(keyword: String): PagingSource<Int, SearchResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<SearchResultEntity>)

    @Query("DELETE FROM searching_result WHERE keyword = :keyword")
    suspend fun clear(keyword: String)

    @Query("SELECT MAX(cachedAt) FROM searching_result WHERE keyword = :keyword")
    suspend fun getLastCachedTime(keyword: String): Long?
}