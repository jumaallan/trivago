package com.trivago.core.executors

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Global executor pools for the whole application.
 *
 *
 * Grouping tasks like this avoids the effects of task starvation (e.g. disk reads don't wait behind
 * webservice requests).
 */
class AppExecutors internal constructor(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    fun networkIO(): Executor = networkIO

    fun mainThread(): Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        private const val THREAD_COUNT = 3

        /** Follow the singleton design pattern with this as the only instance.  */
        private var appExecutors: AppExecutors? = null

        /**
         * Gets the singleton instance of AppExecutors.
         * This can be unsafe with reflection. Option to move to Dagger dependency
         * injection.
         */
        @Synchronized
        @JvmStatic
        fun getInstance(): AppExecutors {
            if (appExecutors == null) {
                appExecutors = AppExecutors()
            }
            return appExecutors as AppExecutors // not sure about this cast
        }
    }
}