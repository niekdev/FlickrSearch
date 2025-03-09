package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

class ClearSearchHistoryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke() {
        searchHistoryRepo.clearSearchHistory()
    }
}
