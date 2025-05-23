package com.morphine_coder.knowmerce.core.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.morphine_coder.knowmerce.BuildConfig
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
object RetrofitModule {

    private const val CONNECT_TIME = 15L
    private const val READ_TIME = 15L
    private const val WRITE_TIME = 15L

    private val gsonBuilder = GsonBuilder().setLenient().serializeNulls().create()
    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gsonBuilder)
    private val scalarConverterFactory: ScalarsConverterFactory = ScalarsConverterFactory.create()

    private  val loggInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        }else{
            HttpLoggingInterceptor.Level.NONE
        }
    }


    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)
            .readTimeout(READ_TIME, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
            .addInterceptor(loggInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addConverterFactory(scalarConverterFactory)
            .build()
    }

}