package dev.niek.flickrsearch.presentation.di

import dev.niek.flickrsearch.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get()) }
}
