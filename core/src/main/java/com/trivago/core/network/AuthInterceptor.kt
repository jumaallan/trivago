package com.trivago.core.network

import com.trivago.core.settings.Settings
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val settings: Settings
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var chainRequest = chain.request()
        chainRequest = if (chainRequest.headers.names().contains("No-Auth")) {
            chainRequest.newBuilder().removeHeader("No-Auth").build()
        } else {
            chainRequest.newBuilder()
                .addHeader("Authorization", settings.getBearerToken().orEmpty())
                .build()
        }
        return chain.proceed(chainRequest)
    }
}