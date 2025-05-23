package com.morphine_coder.knowmerce.common.di

import com.morphine_coder.knowmerce.common.navigation.DefaultNavigator
import com.morphine_coder.knowmerce.common.navigation.Destination
import com.morphine_coder.knowmerce.common.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigatorModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return DefaultNavigator(
            startDestination = Destination.SearchRoute,
        )
    }
}