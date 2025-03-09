package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    suspend fun getSearchHistory(): Flow<List<SearchEntry>>

    suspend fun insertSearchEntry(searchQuery: String)
}
