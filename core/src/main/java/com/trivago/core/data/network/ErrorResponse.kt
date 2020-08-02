package com.trivago.core.data.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)