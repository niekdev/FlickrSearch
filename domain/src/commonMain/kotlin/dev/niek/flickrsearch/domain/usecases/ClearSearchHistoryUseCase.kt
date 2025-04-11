package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

/**
 * Use case for clearing the local search history.
 *
 * @param searchHistoryRepo An instance of [SearchHistoryRepository] used to clear the stored
 * search history.
 */
class ClearSearchHistoryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke() {
        searchHistoryRepo.clearSearchHistory()
    }
}
