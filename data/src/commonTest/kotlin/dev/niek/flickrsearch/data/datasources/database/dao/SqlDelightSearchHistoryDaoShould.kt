package dev.niek.flickrsearch.data.datasources.database.dao

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import dev.niek.flickrsearch.data.datasources.database.FlickrSearchDatabase
import dev.niek.flickrsearch.data.datasources.database.Search_entry
import dev.niek.flickrsearch.data.datasources.database.mappers.SearchEntryEntityListToDomainListMapper
import dev.niek.flickrsearch.data.datasources.database.testDbConnection
import dev.niek.flickrsearch.domain.datasources.database.daos.SearchHistoryDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SqlDelightSearchHistoryDaoShould {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchHistoryDao: SearchHistoryDao = SqlDelightSearchHistoryDao(
        FlickrSearchDatabase(
            driver = testDbConnection(),
            search_entryAdapter = Search_entry.Adapter(
                idAdapter = IntColumnAdapter,
            ),
        ),
        SearchEntryEntityListToDomainListMapper(),
        UnconfinedTestDispatcher(),
    )

    @Before
    fun setup() = runTest {
        // Clearing the data before each test. We'll test if clearing the data actually works later
        searchHistoryDao.clearSearchHistory()
    }

    @Test
    fun `add entry to database on insertSearchEntry call`() = runTest {
        val searchQuery = "Ingolstadt"

        searchHistoryDao.insertSearchEntry(searchQuery)

        val entries = searchHistoryDao.selectFullSearchHistory().first()

        // Assert
        assertEquals(1, entries.size)
        assertEquals(searchQuery, entries[0].query)
    }

    @Test
    fun `return entries in descending order by id on selectFullSearchHistory call`() = runTest {
        listOf("Ingolstadt", "Munich", "Dublin").forEach {
            searchHistoryDao.insertSearchEntry(it)
        }

        val entries = searchHistoryDao.selectFullSearchHistory().first()

        // Assert
        assertEquals(3, entries.size)
        assertEquals("Dublin", entries[0].query)
        assertEquals("Munich", entries[1].query)
        assertEquals("Ingolstadt", entries[2].query)
    }

    @Test
    fun `remove all entries on clearSearchHistory call`() = runTest {
        listOf("Ingolstadt", "Munich").forEach {
            searchHistoryDao.insertSearchEntry(it)
        }

        searchHistoryDao.clearSearchHistory()

        // Assert
        val entries = searchHistoryDao.selectFullSearchHistory().first()
        assertTrue(entries.isEmpty())
    }

    @Test
    fun `return true on hasSearchHistory call when entries exist`() = runTest {
        searchHistoryDao.insertSearchEntry("Munich")

        // Assert
        val hasHistory = searchHistoryDao.hasSearchHistory().first()
        assertTrue(hasHistory)
    }

    @Test
    fun `return false on hasSearchHistory call when no entries exist`() = runTest {
        // Assert
        val hasHistory = searchHistoryDao.hasSearchHistory().first()
        assertFalse(hasHistory)
    }

    @Test
    fun `replace existing entry with same query on insertSearchEntry call`() = runTest {
        searchHistoryDao.insertSearchEntry("Munich")
        searchHistoryDao.insertSearchEntry("Dublin")
        searchHistoryDao.insertSearchEntry("Munich") // Duplicate

        val entries = searchHistoryDao.selectFullSearchHistory().first()

        // Assert
        assertEquals(2, entries.size)
        assertEquals("Munich", entries[0].query)
        assertEquals("Dublin", entries[1].query)
    }
}
