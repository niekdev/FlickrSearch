package dev.niek.flickrsearch.data.di

import dev.niek.flickrsearch.data.repositories.DefaultGreetingRepository
import dev.niek.flickrsearch.domain.repositories.GreetingRepository
import org.koin.dsl.module

val dataModule = module {
    single<GreetingRepository> { DefaultGreetingRepository() }
}
