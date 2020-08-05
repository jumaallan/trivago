package com.trivago.core.utils

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsTest {

    @Test
    fun `given centimeters and convert to inches`() {
        val inches = convertToInches("120")
        Truth.assertThat(inches).isEqualTo("47.244")
    }

    @Test
    fun `given a null string https is the output`() {
        val url: String? = null
        Truth.assertThat("https").isEqualTo(url.toHttps())
    }

    @Test
    fun `given htpp as a string https is the output`() {
        val url: String? = "http"
        Truth.assertThat("https").isEqualTo(url.toHttps())
    }
}