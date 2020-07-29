package com.trivago.core.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.trivago.core.BuildConfig
import com.trivago.core.network.AuthInterceptor
import com.trivago.core.settings.Settings
import com.trivago.core.settings.SettingsConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val networkingModule: Module = module {

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = when (BuildConfig.BUILD_TYPE) {
            "release" -> HttpLoggingInterceptor.Level.NONE
            else -> HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(AuthInterceptor(get()))
            .build()
    }

    single<Retrofit> {
        val baseUrl = ""

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}

val settingsModule: Module = module {

    single {
        androidContext().getSharedPreferences(
            SettingsConstants.TRIVAGO_SETTINGS_NAME,
            Context.MODE_PRIVATE
        )
    }

    single {
        Settings(get())
    }
}

val coreModules: List<Module> = listOf(
    networkingModule,
    settingsModule
)