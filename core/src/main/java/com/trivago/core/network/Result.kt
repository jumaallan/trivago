package com.trivago.core.network

sealed class Result<T> {

    data class Success<T>(val data: T?) : Result<T>()

    data class Error<T>(val error: ResultException) : Result<T>()

    data class Loading<T>(val show: Boolean) : Result<T>()

    fun isSuccess(): Boolean = this is Success

}