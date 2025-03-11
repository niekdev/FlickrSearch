package dev.niek.flickrsearch.data.di

import dev.niek.flickrsearch.data.repositories.DefaultFlickrRepository
import dev.niek.flickrsearch.data.repositories.DefaultSearchHistoryRepository
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import org.koin.dsl.module

val dataModule = module {
    single<SearchHistoryRepository> { DefaultSearchHistoryRepository(get()) }
    single<FlickrRepository> { DefaultFlickrRepository(get()) }
}
