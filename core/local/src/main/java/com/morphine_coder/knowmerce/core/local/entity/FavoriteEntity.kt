package com.morphine_coder.knowmerce.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.morphine_coder.knowmerce.core.model.SearchResult

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val docUrl: String
)
