package dev.niek.flickrsearch.domain.di

import dev.niek.flickrsearch.domain.usecases.GetGreetingUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetGreetingUseCase(greetingRepo = get()) }
}
