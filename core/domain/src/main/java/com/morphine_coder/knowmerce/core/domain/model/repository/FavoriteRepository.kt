package com.morphine_coder.knowmerce.core.domain.model.repository

import androidx.paging.PagingData
import com.morphine_coder.knowmerce.core.model.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
interface FavoriteRepository {

    suspend fun toggleFavorite(item: SearchResult)

    fun getFavoritePaging(): Flow<PagingData<SearchResult>>

    suspend fun addFavorite(item: SearchResult)

    suspend fun removeFavorite(item: SearchResult)

    fun getFavorites(): Flow<List<SearchResult>>
}