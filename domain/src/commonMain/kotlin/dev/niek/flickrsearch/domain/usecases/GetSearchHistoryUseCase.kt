package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class GetSearchHistoryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    operator fun invoke(): Flow<List<SearchEntry>> =
        searchHistoryRepo.getSearchHistory()
}
