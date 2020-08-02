package com.trivago

import org.junit.After
import org.koin.core.context.stopKoin

/**
 * Extended Koin Test
 *      - embed autoclose @after method to close Koin after every test
 *
 */
abstract class AutoCloseKoinTest : KoinTest {

    @After
    fun autoClose() {
        stopKoin()
    }
}

