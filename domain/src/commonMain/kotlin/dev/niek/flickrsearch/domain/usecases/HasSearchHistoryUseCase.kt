package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class HasSearchHistoryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke(): Flow<Boolean> =
        searchHistoryRepo.hasSearchHistory()
}
