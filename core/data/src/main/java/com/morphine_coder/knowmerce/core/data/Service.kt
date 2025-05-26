package com.morphine_coder.knowmerce.core.data

import com.morphine_coder.knowmerce.core.data.response.ImageResponse
import com.morphine_coder.knowmerce.core.data.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
interface Service {

    /**
     * 동영상 검색
     */
    @GET("/v2/search/vclip")
    suspend fun getVideo(
        @Header("Authorization") token: String,
        @QueryMap map: HashMap<String, String>
    ): VideoResponse

    @GET("/v2/search/image")
    suspend fun getImage(
        @Header("Authorization") token: String,
        @QueryMap map: HashMap<String, String>
    ): ImageResponse
}