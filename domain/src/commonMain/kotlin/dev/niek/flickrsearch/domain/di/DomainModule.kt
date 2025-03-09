package dev.niek.flickrsearch.domain.di

import dev.niek.flickrsearch.domain.usecases.GetSearchHistoryUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetSearchHistoryUseCase)
}
