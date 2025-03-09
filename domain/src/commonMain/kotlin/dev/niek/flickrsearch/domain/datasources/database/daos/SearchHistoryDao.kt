package dev.niek.flickrsearch.domain.datasources.database.daos

import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.Flow

interface SearchHistoryDao {

    suspend fun selectFullSearchHistory(): Flow<List<SearchEntry>>

    suspend fun insertSearchEntry(searchQuery: String)

    suspend fun clearSearchHistory()

    suspend fun hasSearchHistory(): Flow<Boolean>
}
