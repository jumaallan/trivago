package com.trivago

import android.app.Application
import android.os.Build
import com.facebook.stetho.Stetho
import com.trivago.core.di.coreModules
import com.trivago.di.appModules
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.koin.core.module.Module
import timber.log.Timber
import javax.annotation.Nullable

class Trivago : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initStetho()
        initTimber()
    }

    private fun initKoin() {
        try {
            startKoin {
                androidLogger()
                androidContext(applicationContext)
                val modules = mutableListOf<Module>().apply {
                    addAll(coreModules)
                    addAll(appModules)
                }
                modules(modules)
            }
        } catch (error: KoinAppAlreadyStartedException) {
            Timber.e(error.localizedMessage)
        }
    }

    private fun initStetho() {
        if (!isRoboUnitTest() && BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                @Nullable
                override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }

    private fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

}