package com.morphine_coder.knowmerce.core.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.morphine_coder.knowmerce.core.local.entity.SearchingEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Dao
interface SearchingDao {

    @Upsert
    suspend fun upsertAll(searchingResults: List<SearchingEntity>)

    @Query("SELECT * FROM searchingentity")
    fun pagingSource(): PagingSource<Int, SearchingEntity>

    @Delete
    fun delete(searchingEntity: SearchingEntity)
}