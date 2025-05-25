package com.morphine_coder.knowmerce.core.common.annotation

import javax.inject.Qualifier

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
enum class DispatcherType {
    IO,
    MAIN,
    DEFAULT,
    UNCONFINED
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: DispatcherType = DispatcherType.DEFAULT)