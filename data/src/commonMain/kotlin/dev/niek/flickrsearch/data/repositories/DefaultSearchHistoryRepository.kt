package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.daos.SearchHistoryDao
import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

class DefaultSearchHistoryRepository(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryRepository {

    override suspend fun getSearchEntry(searchEntryId: Int): Result<SearchEntry> {
        return searchHistoryDao.selectSearchEntryById(searchEntryId)?.run {
            Result.success(SearchEntry(query))
        } ?: Result.failure(RuntimeException("No search entry has been found"))
    }
}
