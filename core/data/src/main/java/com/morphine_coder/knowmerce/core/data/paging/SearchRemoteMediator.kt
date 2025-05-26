package com.morphine_coder.knowmerce.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.monphine_coder.knowmerce.core.data.BuildConfig
import com.morphine_coder.knowmerce.core.common.Logg
import com.morphine_coder.knowmerce.core.data.Service
import com.morphine_coder.knowmerce.core.data.response.toSearchResultEntity
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity
import com.morphine_coder.knowmerce.core.local.entity.SearchResultWithFavorite
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator(
    private val keyword: String,
    private val database: SearchDatabase,
    private val service: Service,
) : RemoteMediator<Int, SearchResultEntity>() {

    private var nextPage: Int? = null

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
                nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        Logg.d("page : ${page}")

        val dao = database.searchDao()

        if (loadType == LoadType.REFRESH) {
            val lastCached = dao.getLastCachedTime(keyword) ?: 0
            val now = System.currentTimeMillis()

            if (now - lastCached <= CACHED_TIME_THRESHOLD) {
                return MediatorResult.Success(endOfPaginationReached = false)
            } else {
                dao.clear(keyword)
            }
        }

        return try {
            val (images, video) = coroutineScope {
                val image = async {
                    service.getImage(
                        token = "KakaoAK ${BuildConfig.KAKAO_KEY}",
                        map = hashMapOf(
                            "query" to keyword,
                            "page" to page.toString(),
                            "size" to (state.config.pageSize).toString()
                        )
                    )
                }

                val video = async {
                    service.getVideo(
                        token = "KakaoAK ${BuildConfig.KAKAO_KEY}",
                        map = hashMapOf(
                            "query" to keyword,
                            "page" to page.toString(),
                            "size" to (state.config.pageSize).toString()
                        )
                    )
                }

                image.await() to video.await()
            }

            val entities = (images.documents.map {
                it.toSearchResultEntity(
                    keyword,
                    page
                )
            } + video.documents.map {
                it.toSearchResultEntity(
                    keyword,
                    page
                )
            }).sortedByDescending { it.timestamp }


            Logg.d("${images.meta.isEnd} , ${video.meta.isEnd}")
            val endOfPaginationReached = images.meta.isEnd || video.meta.isEnd

            nextPage = if (endOfPaginationReached) null else page + 1

            dao.insertAll(entities)

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Logg.e(e)
            MediatorResult.Error(e)
        }
    }
}