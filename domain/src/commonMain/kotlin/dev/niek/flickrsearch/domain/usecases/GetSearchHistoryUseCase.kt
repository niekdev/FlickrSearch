package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving the local search history.
 *
 * @param searchHistoryRepo An instance of [SearchHistoryRepository] used to access the stored
 * search history.
 */
class GetSearchHistoryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    operator fun invoke(): Flow<List<SearchEntry>> =
        searchHistoryRepo.getSearchHistory()
}
