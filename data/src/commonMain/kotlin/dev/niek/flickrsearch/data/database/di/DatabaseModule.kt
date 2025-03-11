package dev.niek.flickrsearch.data.database.di

import dev.niek.flickrsearch.data.database.createDatabase
import dev.niek.flickrsearch.data.database.dao.SqlDelightSearchHistoryDao
import dev.niek.flickrsearch.data.database.mappers.SearchEntryEntityToDomainMapper
import dev.niek.flickrsearch.data.database.sqlDriverFactory
import dev.niek.flickrsearch.domain.daos.SearchHistoryDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createDatabase)

    singleOf(::SearchEntryEntityToDomainMapper)

    single<SearchHistoryDao> { SqlDelightSearchHistoryDao(get(), get()) }
}
