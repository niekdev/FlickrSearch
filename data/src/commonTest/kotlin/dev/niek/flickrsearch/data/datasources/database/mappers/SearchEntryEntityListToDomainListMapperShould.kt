package dev.niek.flickrsearch.data.datasources.database.mappers

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import dev.niek.flickrsearch.data.datasources.database.Search_entry as SearchEntryEntity

class SearchEntryEntityListToDomainListMapperShould {

    private val mapper = SearchEntryEntityListToDomainListMapper()

    @Test
    fun `map list of entities to domain objects`() {
        val entities = listOf(
            SearchEntryEntity(id = 0, query = "Ingolstadt"),
            SearchEntryEntity(id = 1, query = "Munich"),
            SearchEntryEntity(id = 2, query = "Dublin"),
        )

        val result = mapper(entities)

        // Assert
        assertEquals(3, result.size)
        assertEquals("Ingolstadt", result[0].query)
        assertEquals("Munich", result[1].query)
        assertEquals("Dublin", result[2].query)
    }

    @Test
    fun `map empty entity list to empty domain list`() {
        val emptyList = emptyList<SearchEntryEntity>()

        val result = mapper(emptyList)

        // Assert
        assertTrue(result.isEmpty())
    }
}
