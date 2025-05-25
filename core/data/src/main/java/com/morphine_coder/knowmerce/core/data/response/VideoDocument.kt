package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity
import com.morphine_coder.knowmerce.core.model.SearchResult

data class VideoDocument(
    @SerializedName("author")
    val author: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("play_time")
    val playTime: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

fun VideoDocument.toSearchingResult(): SearchResult {
    return SearchResult(
        imageUrl = thumbnail,
        timestamp = datetime
    )
}

fun VideoDocument.toSearchResultEntity(keyword: String, page: Int): SearchResultEntity {
    return SearchResultEntity(
        keyword = keyword,
        page = page,
        imageUrl = thumbnail,
        timestamp = datetime,
        cachedAt = System.currentTimeMillis(),
    )
}