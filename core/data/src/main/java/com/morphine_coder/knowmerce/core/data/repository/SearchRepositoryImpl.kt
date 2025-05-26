package com.morphine_coder.knowmerce.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.morphine_coder.knowmerce.core.data.Service
import com.morphine_coder.knowmerce.core.data.paging.SearchRemoteMediator
import com.morphine_coder.knowmerce.core.domain.model.repository.SearchRepository
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import com.morphine_coder.knowmerce.core.local.dao.FavoritesDao
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity
import com.morphine_coder.knowmerce.core.local.entity.SearchResultWithFavorite
import com.morphine_coder.knowmerce.core.local.entity.toModel
import com.morphine_coder.knowmerce.core.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
class SearchRepositoryImpl @Inject constructor(
    private val database: SearchDatabase,
    private val service: Service
): SearchRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun search(keyword: String): Flow<PagingData<SearchResult>> {
        val pagingSourceFactory = {
            database.searchDao().pagingSource2(keyword)
        }

        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = SearchRemoteMediator(
                keyword = keyword,
                database = database,
                service = service
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { entity ->
            entity.map {
                it.toModelWithFavorite(database.favoriteDao())
            }
        }

    }
}

suspend fun SearchResultEntity.toModelWithFavorite(favoritesDao: FavoritesDao): SearchResult {
    val isFavorite = favoritesDao.isFavorite(this.docUrl)
    return SearchResult(
        docUrl = docUrl,
        isFavorite = isFavorite,
        imageUrl = imageUrl,
        timestamp = timestamp,
    )
}