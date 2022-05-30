package com.yomemo.yomovies.models

import java.io.Serializable

sealed class Response<out T> : Serializable {

    object Loading : Response<Nothing>()

    data class Error constructor(
        val error: Exception,
    ) : Response<Nothing>()

    object Empty : Response<Nothing>()

    data class Success<T> constructor(
        val data: T,
    ) : Response<T>()
}