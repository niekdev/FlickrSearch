package dev.niek.flickrsearch.domain.daos

import dev.niek.flickrsearch.domain.models.SearchEntry

interface SearchHistoryDao {

    suspend fun selectSearchEntryById(searchEntryId: Int): SearchEntry?
}
