package com.morphine_coder.knowmerce.common.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 23.
 */
inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json
) = object: NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let(json::decodeFromString)
    }

    override fun parseValue(value: String): T {
        return json.decodeFromString(value)
    }

    override fun serializeAsValue(value: T): String {
        return json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }

}