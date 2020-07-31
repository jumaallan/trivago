package com.trivago.di

import androidx.room.Room
import com.trivago.data.Database
import com.trivago.data.repository.CharacterSearchRepository
import com.trivago.ui.viewmodel.CharacterSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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

private val daoModule: Module = module {
    single { get<Database>().characterDao() }
}

private val repositoryModule: Module = module {
    single { CharacterSearchRepository(get(), get()) }
}

private val viewModelModule: Module = module {
    viewModel { CharacterSearchViewModel(get()) }
}

val appModules: List<Module> = listOf(
    databaseModule,
    daoModule,
    repositoryModule,
    viewModelModule
)