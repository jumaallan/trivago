package com.trivago.di

import androidx.room.Room
import com.trivago.data.Database
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

private val databaseModule: Module = module {
    single {
        Room
            .inMemoryDatabaseBuilder(androidContext(), Database::class.java)
            .allowMainThreadQueries()
            .build()
    }
}

private val daoModule: Module = module {
    single { get<Database>().characterDao() }
}

val testModules: List<Module> = listOf(
    databaseModule,
    daoModule
)