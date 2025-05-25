package com.morphine_coder.knowmerce.core.designsystem.snackbar

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */

data class SnackBarEvent(
    val message: String,
    val action: SnackBarAction? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short
)

data class SnackBarAction(
    val name: String,
    val action: suspend () -> Unit
)
object SnackBarController {

    private val _events = Channel<SnackBarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent) {
        _events.send(event)
    }

}