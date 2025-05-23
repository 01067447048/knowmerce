package com.morphine_coder.knowmerce.common.navigation

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
sealed interface Destination {

    data object SearchRoute : Destination
    data object SavedListRoute : Destination
}