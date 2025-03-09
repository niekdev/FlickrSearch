package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.daos.SearchHistoryDao
import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class DefaultSearchHistoryRepository(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryRepository {

    override suspend fun getSearchHistory(): Flow<List<SearchEntry>> {
        return searchHistoryDao.selectFullSearchHistory()
    }
}
