package dev.niek.flickrsearch.presentation.di

import dev.niek.flickrsearch.presentation.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
}
