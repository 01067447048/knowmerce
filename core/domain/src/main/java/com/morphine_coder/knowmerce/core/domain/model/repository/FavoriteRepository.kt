package com.morphine_coder.knowmerce.core.domain.model.repository

import com.morphine_coder.knowmerce.core.model.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
interface FavoriteRepository {

    suspend fun toggleFavorite(item: SearchResult)

    fun getFavorites(): Flow<List<SearchResult>>
}