package com.trivago.core.utils

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Responsible for converting Flow<List<T>> to a List<>
 *
 * @param T
 * @return a List from a Flow<List<>>
 */
@FlowPreview
suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()


/**
 * Utility function to convert Centimeters to Inches
 *
 * @param centimeters
 * @return returns the inches, as a string
 */
fun convertToInches(centimeters: String): String =
    (BigDecimal(centimeters.toDouble() * 0.393701).setScale(3, RoundingMode.HALF_EVEN)).toString()