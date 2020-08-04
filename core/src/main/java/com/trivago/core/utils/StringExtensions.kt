package com.trivago.core.utils

/**
 * Responsible for forcing an http url to https
 *
 * @return the url in https, if an http url format is passed
 */
fun String.toHttps(): String =
    if (!this.contains("https")) {
        this.replace("http", "https")
    } else this
