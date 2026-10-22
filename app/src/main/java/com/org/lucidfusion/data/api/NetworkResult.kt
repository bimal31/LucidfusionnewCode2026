package com.org.lucidfusion.data.api

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T, val statusCode: Int = 200) : NetworkResult<T>()
    data class Error(val message: String, val statusCode: Int = -1) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
