package com.trivago.core.utils

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Utility function to convert Centimeters to Inches
 *
 * @param centimeters
 * @return returns the inches, as a string
 */
fun convertToInches(centimeters: String): String {
    return when {
        centimeters.isBlank() -> {
            "0"
        }
        else -> {
            (BigDecimal(centimeters.toDouble() * 0.393701).setScale(
                3,
                RoundingMode.HALF_EVEN
            )).toString()
        }
    }
}
