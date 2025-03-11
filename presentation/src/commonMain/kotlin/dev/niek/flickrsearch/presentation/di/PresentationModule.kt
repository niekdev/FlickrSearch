package dev.niek.flickrsearch.presentation.di

import dev.niek.flickrsearch.presentation.screens.history.HistoryViewModel
import dev.niek.flickrsearch.presentation.screens.main.MainViewModel
import dev.niek.flickrsearch.presentation.screens.results.ResultsViewModel
import dev.niek.flickrsearch.presentation.screens.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ResultsViewModel(get()) }
}
