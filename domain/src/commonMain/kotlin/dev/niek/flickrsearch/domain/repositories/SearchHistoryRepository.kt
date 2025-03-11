package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.SearchEntry

interface SearchHistoryRepository {

    suspend fun getSearchEntry(searchEntryId: Int): Result<SearchEntry>
}
