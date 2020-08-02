package com.trivago.base

import com.trivago.dispatcher.MockRequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.test.KoinTest

open class BaseTest : KoinTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = MockRequestDispatcher()
            start(8080)
        }
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

}