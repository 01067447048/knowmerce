package com.morphine_coder.knowmerce.core.data.di

import com.morphine_coder.knowmerce.core.data.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideService(
        retrofit: Retrofit
    ): Service {
        return retrofit.create(Service::class.java)
    }

}