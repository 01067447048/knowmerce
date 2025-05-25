package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("documents")
    val documents: List<VideoDocument>,
    @SerializedName("meta")
    val meta: VideoMeta
)