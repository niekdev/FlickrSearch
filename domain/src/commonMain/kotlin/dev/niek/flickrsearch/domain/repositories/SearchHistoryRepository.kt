package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    fun getSearchHistory(): Flow<List<SearchEntry>>

    suspend fun insertSearchEntry(searchQuery: String)

    suspend fun clearSearchHistory()

    suspend fun hasSearchHistory(): Flow<Boolean>
}
