package dev.niek.flickrsearch.data.database.mappers

import dev.niek.flickrsearch.data.database.Search_entry as SearchEntryEntity
import dev.niek.flickrsearch.domain.models.SearchEntry as SearchEntryDomain

class SearchEntryEntityToDomainMapper : Function1<SearchEntryEntity, SearchEntryDomain> {

    override fun invoke(searchEntry: SearchEntryEntity): SearchEntryDomain {
        return SearchEntryDomain(query = searchEntry.query)
    }
}
