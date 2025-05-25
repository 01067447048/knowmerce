package com.morphine_coder.knowmerce.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morphine_coder.knowmerce.core.model.SearchResult

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Entity
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: String
)

fun FavoriteEntity.toModel() = SearchResult(
    imageUrl = thumbnail,
    timestamp = timeStamp
)

fun SearchResult.toEntity() = FavoriteEntity(
    thumbnail = imageUrl,
    timeStamp = timestamp
)
