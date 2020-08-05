package com.trivago.base

import com.trivago.dispatcher.StarWarsRequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.test.KoinTest

open class BaseTest : KoinTest {

    protected lateinit var mockWebServer: MockWebServer

    @Before
    open fun setup() {
        mockWebServer = MockWebServer().apply {
            dispatcher = StarWarsRequestDispatcher()
            start(8080)
        }
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}