package com.morphine_coder.knowmerce.common

import android.util.Log
import com.morphine_coder.knowmerce.BuildConfig

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
object Logg {
    private const val LIMIT_LENGTH = 80

    private fun tag(): String {
        val traceElement = Thread.currentThread().stackTrace[4]
        val fileName = traceElement.fileName
        val lineNumber = traceElement.lineNumber
        val link = "($fileName:$lineNumber)"
        val className = traceElement.className.substringAfterLast(".")
        val methodName = traceElement.methodName
        val path = "App# $className.$methodName"
        val result = if(path.length + link.length > LIMIT_LENGTH) {
            val splitPath = path.take(LIMIT_LENGTH - link.length)
            "$splitPath...$link"
        }else{
            "$path$link"
        }
        return result
    }

    fun v(message: String?) {
        if (BuildConfig.DEBUG)
            Log.v(tag(), message ?: "")
    }

    fun d(message: String?) {
        if (BuildConfig.DEBUG)
            Log.d(tag(), message ?: "")
    }

    fun i(message: String?) {
        if (BuildConfig.DEBUG)
            Log.i(tag(), message ?:"")
    }

    fun w(message: String?) {
        if (BuildConfig.DEBUG)
            Log.w(tag(), message ?: "")
    }

    fun w(throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.w(tag(), throwable?.localizedMessage ?: "")
            throwable?.printStackTrace()
        }
    }

    fun w(exception: Exception?) {
        if (BuildConfig.DEBUG) {
            Log.w(tag(), exception?.localizedMessage ?: "")
            exception?.printStackTrace()
        }
    }

    fun e(message: String?) {
        if (BuildConfig.DEBUG)
            Log.e(tag(), message ?: "")
    }

    fun e(message: String?, throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag(), message ?: "")
            throwable?.printStackTrace()
        }
    }

    fun e(throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag(), throwable?.localizedMessage ?: "")
            throwable?.printStackTrace()
        }
    }

    fun e(exception: Exception?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag(), exception?.localizedMessage ?: "")
            exception?.printStackTrace()
        }
    }
}