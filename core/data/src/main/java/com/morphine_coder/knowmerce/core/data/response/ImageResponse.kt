package com.morphine_coder.knowmerce.core.data.response


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("documents")
    val documents: List<ImageDocument>,
    @SerializedName("meta")
    val meta: ImageMeta
)

