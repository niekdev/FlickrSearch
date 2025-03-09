package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.datasources.database.daos.SearchHistoryDao
import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class DefaultSearchHistoryRepository(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryRepository {

    override suspend fun getSearchHistory(): Flow<List<SearchEntry>> {
        return searchHistoryDao.selectFullSearchHistory()
    }

    override suspend fun insertSearchEntry(searchQuery: String) {
        searchHistoryDao.insertSearchEntry(searchQuery)
    }

    override suspend fun clearSearchHistory() {
        searchHistoryDao.clearSearchHistory()
    }

    override suspend fun hasSearchHistory(): Flow<Boolean> {
        return searchHistoryDao.hasSearchHistory()
    }
}
