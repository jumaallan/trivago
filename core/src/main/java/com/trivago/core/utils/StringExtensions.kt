package com.trivago.core.utils

/**
 * Responsible for forcing an http url to https
 *
 * @return the url in https, if an http url format is passed
 */
fun String?.toHttps(): String {
    // Default to https
    val http = "http"
    val https = "https"
    val url = this ?: https
    return if (!url.contains(https)) {
        url.replace(http, https)
    } else url
}