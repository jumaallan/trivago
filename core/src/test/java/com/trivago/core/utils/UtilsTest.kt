package com.trivago.core.utils

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UtilsTest {

    @Test
    fun `given centimeters and convert to inches`() {
        val inches = "120".toInches()
        Truth.assertThat(inches).isEqualTo("47.244")
    }

    @Test
    fun `given empty centimeter and convert to inches`() {
        val inches = "".toInches()
        Truth.assertThat(inches).isEqualTo("0")
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