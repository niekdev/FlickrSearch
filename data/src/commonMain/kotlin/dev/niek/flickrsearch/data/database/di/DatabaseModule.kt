package dev.niek.flickrsearch.data.database.di

import dev.niek.flickrsearch.data.database.createDatabase
import dev.niek.flickrsearch.data.database.dao.SqlDelightGreetingDao
import dev.niek.flickrsearch.data.database.mappers.GreetingEntityToDomainMapper
import dev.niek.flickrsearch.data.database.sqlDriverFactory
import dev.niek.flickrsearch.domain.daos.GreetingDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    factory { sqlDriverFactory() }
    singleOf(::createDatabase)

    singleOf(::GreetingEntityToDomainMapper)

    single<GreetingDao> { SqlDelightGreetingDao(get(), get()) }
}
