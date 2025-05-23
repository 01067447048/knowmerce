package com.morphine_coder.knowmerce.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Entity
data class SearchingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: String
)
