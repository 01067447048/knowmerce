package com.morphine_coder.knowmerce.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.monphine_coder.knowmerce.core.data.BuildConfig
import com.morphine_coder.knowmerce.core.common.Logg
import com.morphine_coder.knowmerce.core.data.Service
import com.morphine_coder.knowmerce.core.data.response.toSearchResultEntity
import com.morphine_coder.knowmerce.core.data.response.toSearchingResult
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val keyword: String,
    private val database: SearchDatabase,
    private val service: Service,
): RemoteMediator<Int, SearchResultEntity>() {

    companion object {
        private const val CACHED_TIME_THRESHOLD = 5 * 60 * 1000
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchResultEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.page + 1
            }
        }

        val dao = database.searchDao()

        if (loadType == LoadType.REFRESH) {
            val lastCached = dao.getLastCachedTime(keyword) ?: 0
            val now = System.currentTimeMillis()

            if (now - lastCached <= CACHED_TIME_THRESHOLD) {
                return MediatorResult.Success(endOfPaginationReached = false) // 캐시가 유효하면 네트워크 호출 생략
            } else {
                dao.clear(keyword) // 만료된 캐시 삭제
            }
        }

        return try {
            val images = service.getImage(
                token = "KakaoAK ${BuildConfig.KAKAO_KEY}",
                map = hashMapOf(
                    "query" to keyword,
                    "page" to page.toString(),
                    "size" to (state.config.pageSize / 2).toString()
                )
            ).documents.map {
                it.toSearchResultEntity(keyword, page)
            }

            val video = service.getVideo(
                token = "KakaoAK ${BuildConfig.KAKAO_KEY}",
                map = hashMapOf(
                    "query" to keyword,
                    "page" to page.toString(),
                    "size" to (state.config.pageSize / 2).toString()
                )
            ).documents.map {
                it.toSearchResultEntity(keyword, page)
            }

            val entities = (images + video).sortedBy { it.timestamp }

            dao.insertAll(entities)

            MediatorResult.Success(endOfPaginationReached = images.isEmpty() && video.isEmpty())
        } catch (e: Exception) {
            Logg.e(e)
            MediatorResult.Error(e)
        }
    }
}