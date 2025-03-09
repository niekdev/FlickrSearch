package dev.niek.flickrsearch.data.datasources.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import dev.niek.flickrsearch.data.datasources.database.FlickrSearchDatabase
import dev.niek.flickrsearch.data.datasources.database.mappers.SearchEntryEntityListToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.database.daos.SearchHistoryDao
import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightSearchHistoryDao(
    private val database: FlickrSearchDatabase,
    private val searchEntryEntityListToDomainListMapper: SearchEntryEntityListToDomainListMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SearchHistoryDao {

    private val queries get() = database.searchEntryQueries

    override suspend fun selectFullSearchHistory(): Flow<List<SearchEntry>> =
        queries.selectFullSearchHistory()
            .asFlow()
            .mapToList(dispatcher)
            .map(searchEntryEntityListToDomainListMapper)

    override suspend fun insertSearchEntry(searchQuery: String) {
        queries.insertSearchEntry(searchQuery)
    }

    override suspend fun clearSearchHistory() {
        queries.clearSearchHistory()
    }

    override suspend fun hasSearchHistory(): Flow<Boolean> =
        queries.hasSearchHistory()
            .asFlow()
            .mapToOne(dispatcher)
}
