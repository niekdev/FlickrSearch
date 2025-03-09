package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

class InsertSearchEntryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke(searchQuery: String) {
        searchHistoryRepo.insertSearchEntry(searchQuery)
    }
}
