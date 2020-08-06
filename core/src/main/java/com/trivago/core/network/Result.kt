package com.trivago.core.network

sealed class Result<out T> {

    /**
     * This is used to represent successful results
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Used to represent errors that resulted from an IO operation
     */
    data class Error(
        val errorMessage: String? = null,
        val exception: Throwable? = null
    ) : Result<Nothing>()

    /**
     * Used to represent Loading status of an IO operation
     */
    object Loading : Result<Nothing>()

    fun isSuccess(): Boolean = this is Success
}