package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName
import com.morphine_coder.knowmerce.core.model.SearchingResult

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

fun ImageDocument.toSearchingResult(): SearchingResult {
    return SearchingResult(
        thumbnail = thumbnailUrl,
        timeStamp = datetime
    )
}