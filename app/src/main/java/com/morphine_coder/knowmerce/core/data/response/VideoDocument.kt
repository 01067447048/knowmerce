package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName
import com.morphine_coder.knowmerce.core.model.SearchingResult

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

fun VideoDocument.toSearchingResult(): SearchingResult {
    return SearchingResult(
        thumbnail = thumbnail,
        timeStamp = datetime
    )
}