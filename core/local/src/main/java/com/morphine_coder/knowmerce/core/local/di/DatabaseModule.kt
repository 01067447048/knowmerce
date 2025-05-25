package com.morphine_coder.knowmerce.core.local.di

import android.content.Context
import androidx.room.Room
import com.morphine_coder.knowmerce.core.local.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSearchDatabase(
        @ApplicationContext context: Context
    ): SearchDatabase {
        return Room.databaseBuilder(
            context,
            SearchDatabase::class.java,
            "search_database"
        ).build()
    }
}