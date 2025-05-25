package com.morphine_coder.knowmerce.core.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morphine_coder.knowmerce.core.model.SearchResult

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Entity(
    tableName = "searching_result",
    primaryKeys = ["keyword", "page"]
)
data class SearchResultEntity(
    val keyword: String,
    val page: Int,
    val imageUrl: String,
    val timestamp: String,
    val cachedAt: Long
)

fun SearchResultEntity.toModel(): SearchResult {
    return SearchResult(
        imageUrl = imageUrl,
        timestamp = timestamp
    )
}
