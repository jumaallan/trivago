package com.trivago.di

import androidx.room.Room
import com.trivago.data.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private val databaseModule: Module = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "trivago"
        ).build()
    }
}

val appModules: List<Module> = listOf(
    databaseModule
)