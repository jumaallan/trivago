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
}