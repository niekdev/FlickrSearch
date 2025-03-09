package dev.niek.flickrsearch.data.database.mappers

import dev.niek.flickrsearch.data.database.Search_entry as SearchEntryEntity
import dev.niek.flickrsearch.domain.models.SearchEntry as SearchEntryDomain

class SearchEntryEntityListToDomainListMapper :
    Function1<List<SearchEntryEntity>, List<SearchEntryDomain>> {

    override fun invoke(searchHistory: List<SearchEntryEntity>): List<SearchEntryDomain> {
        return searchHistory.map { SearchEntryDomain(query = it.query) }
    }
}
