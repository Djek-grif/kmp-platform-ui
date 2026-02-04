package com.djekgrif.nativeuisimple.data.model.common

sealed interface ApiResult<out T> {
    data class Success<T>(val value: T) : ApiResult<T>
    data class Error(val statusCode: Int) : ApiResult<Nothing> {
        object StatusCode {
            const val INVALID_CREDENTIALS = 5000

            const val NETWORK_UNAVAILABLE = 1000
            const val TIMEOUT = 1001
            const val SERIALIZATION = 1002
            const val UNKNOWN = 2000
        }
    }
}