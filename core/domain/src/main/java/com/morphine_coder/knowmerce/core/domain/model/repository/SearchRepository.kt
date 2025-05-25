package com.morphine_coder.knowmerce.core.domain.model.repository

import androidx.paging.PagingData
import com.morphine_coder.knowmerce.core.model.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
interface SearchRepository {

    fun search(keyword: String): Flow<PagingData<SearchResult>>
}