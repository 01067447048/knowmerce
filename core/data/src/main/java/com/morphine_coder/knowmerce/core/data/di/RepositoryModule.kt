package com.morphine_coder.knowmerce.core.data.di

import com.morphine_coder.knowmerce.core.data.Service
import com.morphine_coder.knowmerce.core.data.repository.SearchRepositoryImpl
import com.morphine_coder.knowmerce.core.domain.model.repository.SearchRepository
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        database: SearchDatabase,
        service: Service
    ): SearchRepository = SearchRepositoryImpl(
        database, service
    )

}