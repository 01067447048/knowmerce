package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName
import com.morphine_coder.knowmerce.core.local.entity.SearchResultEntity
import com.morphine_coder.knowmerce.core.model.SearchResult

data class ImageDocument(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("display_sitename")
    val displaySitename: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("width")
    val width: Int
)

fun ImageDocument.toSearchingResult(): SearchResult {
    return SearchResult(
        imageUrl = thumbnailUrl,
        timestamp = datetime
    )
}

fun ImageDocument.toSearchResultEntity(keyword: String, page: Int): SearchResultEntity {
    return SearchResultEntity(
        keyword = keyword,
        page = page,
        imageUrl = thumbnailUrl,
        timestamp = datetime,
        cachedAt = System.currentTimeMillis()
    )
}