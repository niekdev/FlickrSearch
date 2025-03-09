package dev.niek.flickrsearch.domain.di

import dev.niek.flickrsearch.domain.usecases.ClearSearchHistoryUseCase
import dev.niek.flickrsearch.domain.usecases.GetSearchHistoryUseCase
import dev.niek.flickrsearch.domain.usecases.HasSearchHistoryUseCase
import dev.niek.flickrsearch.domain.usecases.InsertSearchEntryUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetSearchHistoryUseCase)
    singleOf(::InsertSearchEntryUseCase)
    singleOf(::ClearSearchHistoryUseCase)
    singleOf(::HasSearchHistoryUseCase)
}
