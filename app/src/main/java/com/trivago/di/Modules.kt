package com.trivago.di

import androidx.room.Room
import com.trivago.data.Database
import com.trivago.data.repository.CharacterFilmsRepository
import com.trivago.data.repository.CharacterPlanetRepository
import com.trivago.data.repository.CharacterSearchRepository
import com.trivago.data.repository.CharacterSpeciesRepository
import com.trivago.ui.viewmodel.CharacterDetailsViewModel
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 *  App DI Module
 *
 *  This file is responsible for setting up and injecting the following
 *      - Database (Room DB)
 *      - DAOs
 *      - Repository
 *      - ViewModels
 *
 * The modules are only available on the app module. They are app specific and therefore should
 * be managed centrally inside the app module.
 *
 * This setup also allows us to re-use the globally injected modules, to avoid duplication.
 */
private val databaseModule: Module = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "trivago-db"
        ).build()
    }
}

private val daoModule: Module = module {
    single { get<Database>().characterDao() }
}

private val repositoryModule: Module = module {
    single { CharacterSearchRepository(get(), get()) }
    single { CharacterSpeciesRepository(get()) }
    single { CharacterFilmsRepository(get()) }
    single { CharacterPlanetRepository(get()) }
}

private val viewModelModule: Module = module {
    viewModel { CharacterSearchViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get(), get(), get()) }
}

val appModules: List<Module> = listOf(
    databaseModule,
    daoModule,
    repositoryModule,
    viewModelModule
)