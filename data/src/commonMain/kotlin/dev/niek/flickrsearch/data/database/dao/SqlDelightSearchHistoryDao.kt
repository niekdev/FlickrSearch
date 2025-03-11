package dev.niek.flickrsearch.data.database.dao

import dev.niek.flickrsearch.data.database.FlickrSearchDatabase
import dev.niek.flickrsearch.data.database.mappers.SearchEntryEntityToDomainMapper
import dev.niek.flickrsearch.domain.daos.SearchHistoryDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SqlDelightSearchHistoryDao(
    private val database: FlickrSearchDatabase,
    private val searchEntryEntityToDomainMapper: SearchEntryEntityToDomainMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SearchHistoryDao {

    private val query get() = database.searchEntryQueries

    override suspend fun selectSearchEntryById(searchEntryId: Int) = withContext(dispatcher) {
        query.selectSearchEntryById(id = searchEntryId)
            .executeAsOneOrNull()
            ?.run(searchEntryEntityToDomainMapper)
    }
}
