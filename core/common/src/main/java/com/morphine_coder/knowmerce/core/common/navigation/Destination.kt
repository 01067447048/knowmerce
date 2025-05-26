package com.morphine_coder.knowmerce.core.common.navigation

import kotlinx.serialization.Serializable

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
sealed interface Destination {

    @Serializable
    data object SearchRoute : Destination

    @Serializable
    data object SavedListRoute : Destination
}