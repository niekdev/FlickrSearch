package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

/**
 * Use case for inserting a local search history entry.
 *
 * @param searchHistoryRepo An instance of [SearchHistoryRepository] used to insert a local search
 * history entry.
 */
class InsertSearchEntryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke(searchQuery: String) {
        searchHistoryRepo.insertSearchEntry(searchQuery)
    }
}
