package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing local search history.
 */
interface SearchHistoryRepository {

    /**
     * Retrieves the user's search history as a stream of data.
     *
     * @return A [Flow] emitting a list of [SearchEntry] objects representing the search history.
     */
    fun getSearchHistory(): Flow<List<SearchEntry>>

    /**
     * Inserts a new search query into the search history.
     *
     * @param searchQuery The search term to be added to the history.
     */
    suspend fun insertSearchEntry(searchQuery: String)

    /**
     * Clears all entries from the search history.
     */
    suspend fun clearSearchHistory()

    /**
     * Checks if there are any entries in the search history.
     *
     * @return A [Flow] emitting `true` if there is at least one entry in the search history, or
     * `false` if it is empty.
     */
    suspend fun hasSearchHistory(): Flow<Boolean>
}
