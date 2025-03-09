package dev.niek.flickrsearch.data.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.niek.flickrsearch.data.database.FlickrSearchDatabase
import dev.niek.flickrsearch.data.database.mappers.SearchEntryEntityListToDomainListMapper
import dev.niek.flickrsearch.domain.daos.SearchHistoryDao
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
}
