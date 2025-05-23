package com.morphine_coder.knowmerce.common.navigation

import androidx.navigation.NavOptionsBuilder

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
sealed interface NavigationAction {

    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ): NavigationAction

    data object NavigateUp: NavigationAction
}