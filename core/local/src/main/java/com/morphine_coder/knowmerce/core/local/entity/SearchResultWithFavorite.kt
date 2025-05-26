package com.morphine_coder.knowmerce.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.morphine_coder.knowmerce.core.model.SearchResult

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 26.
 */
data class SearchResultWithFavorite(
    @Embedded
    val entity: SearchResultEntity,
    @ColumnInfo(name = "isFavorite") val isFavorite: Int
)

fun SearchResultWithFavorite.toModel(): SearchResult {
    return SearchResult(
        imageUrl = entity.imageUrl,
        timestamp = entity.timestamp,
        docUrl = entity.docUrl,
        isFavorite = isFavorite == 1
    )
}
