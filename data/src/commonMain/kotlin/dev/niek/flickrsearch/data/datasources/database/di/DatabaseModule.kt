package dev.niek.flickrsearch.data.datasources.database.di

import dev.niek.flickrsearch.data.datasources.database.createDatabase
import dev.niek.flickrsearch.data.datasources.database.dao.SqlDelightSearchHistoryDao
import dev.niek.flickrsearch.data.datasources.database.mappers.SearchEntryEntityListToDomainListMapper
import dev.niek.flickrsearch.data.datasources.database.sqlDriverFactory
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.database.daos.SearchHistoryDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createDatabase)

    singleOf(::SearchEntryEntityListToDomainListMapper)
    singleOf(::SearchPhotosResponseToDomainListMapper)

    single<SearchHistoryDao> { SqlDelightSearchHistoryDao(get(), get()) }
}
