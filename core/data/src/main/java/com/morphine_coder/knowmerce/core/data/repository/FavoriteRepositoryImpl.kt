package com.morphine_coder.knowmerce.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.morphine_coder.knowmerce.core.domain.model.repository.FavoriteRepository
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import com.morphine_coder.knowmerce.core.local.entity.FavoriteEntity
import com.morphine_coder.knowmerce.core.local.entity.toModel
import com.morphine_coder.knowmerce.core.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
class FavoriteRepositoryImpl @Inject constructor(
    private val database: SearchDatabase,
): FavoriteRepository {

    override suspend fun toggleFavorite(item: SearchResult) {
        val favoritesDao = database.favoriteDao()
        if (item.isFavorite) {
            favoritesDao.delete(
                FavoriteEntity(item.docUrl)
            )
        } else {
            favoritesDao.insert(
                FavoriteEntity(item.docUrl)
            )
        }
    }

    override fun getFavoritePaging(): Flow<PagingData<SearchResult>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                database.favoriteDao().pagingFavorites()
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }

    override suspend fun addFavorite(item: SearchResult) {
        database.favoriteDao().insert(
            FavoriteEntity(item.docUrl)
        )
    }

    override suspend fun removeFavorite(item: SearchResult) {
        database.favoriteDao().delete(
            FavoriteEntity(item.docUrl)
        )
    }

    override fun getFavorites(): Flow<List<SearchResult>> {
        return database.searchDao().getAllFavorites().map {
            it.map { entity ->
                entity.toModel()
            }
        }
    }

}