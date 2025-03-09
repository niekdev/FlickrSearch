package dev.niek.flickrsearch.domain.daos

import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.Flow

interface SearchHistoryDao {

    suspend fun selectFullSearchHistory(): Flow<List<SearchEntry>>

    suspend fun insertSearchEntry(searchQuery: String)
}
