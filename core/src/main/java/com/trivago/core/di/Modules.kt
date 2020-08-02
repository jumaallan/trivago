package com.trivago.core.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.trivago.core.BuildConfig
import com.trivago.core.data.api.StarWarsAPI
import com.trivago.core.executors.AppExecutors
import com.trivago.core.network.AuthInterceptor
import com.trivago.core.settings.Settings
import com.trivago.core.settings.SettingsConstants
import com.trivago.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

/**
 *  Core DI Module
 *
 *  This file is responsible for setting up and injecting the following
 *      - network (OKHTTP and Retrofit)
 *      - Retrofit API
 *      - Shared Preference
 *      - App Executors
 *
 * The modules are available globally across the app. This makes it easy to manage them centrally
 */
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

    single {

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }
}

val apiModule: Module = module {
    single<StarWarsAPI> { get<Retrofit>().create() }
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

private val appExecutorsModule: Module = module {
    single { AppExecutors.getInstance() }
}

val coreModules: List<Module> = listOf(
    networkingModule,
    apiModule,
    settingsModule,
    appExecutorsModule
)