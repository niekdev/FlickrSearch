package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository

class GetSearchEntryUseCase(private val searchHistoryRepo: SearchHistoryRepository) {

    suspend operator fun invoke(): Result<SearchEntry> = searchHistoryRepo.getSearchEntry(0)
}
