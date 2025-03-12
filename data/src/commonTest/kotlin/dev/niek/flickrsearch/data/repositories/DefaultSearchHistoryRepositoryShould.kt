package dev.niek.flickrsearch.data.repositories

import app.cash.turbine.test
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import dev.niek.flickrsearch.domain.datasources.database.daos.SearchHistoryDao
import dev.niek.flickrsearch.domain.models.SearchEntry
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DefaultSearchHistoryRepositoryShould {

    private val searchHistoryDao: SearchHistoryDao = mock<SearchHistoryDao> {
        every { selectFullSearchHistory() } returns flowOf(emptyList())
        everySuspend { hasSearchHistory() } returns flowOf(false)
        everySuspend { insertSearchEntry(any()) } returns Unit
        everySuspend { clearSearchHistory() } returns Unit
    }

    private val repository = DefaultSearchHistoryRepository(searchHistoryDao)

    @Test
    fun `return flow from DAO on getSearchHistory call`() = runTest {
        val searchEntries = listOf(
            SearchEntry("Dublin"),
            SearchEntry("Ingolstadt")
        )
        every { searchHistoryDao.selectFullSearchHistory() } returns flowOf(searchEntries)

        repository.getSearchHistory().test {
            val result = awaitItem()
            assertEquals(searchEntries, result)
            assertEquals(2, result.size)
            assertEquals("Dublin", result[0].query)
            assertEquals("Ingolstadt", result[1].query)
            awaitComplete()
        }

        // Assert
        verify { searchHistoryDao.selectFullSearchHistory() }
    }

    @Test
    fun `call the DAO insertion function on insertSearchEntry call`() = runTest {
        val searchQuery = "Munich"

        repository.insertSearchEntry(searchQuery)

        // Assert
        verifySuspend(exactly(1)) { searchHistoryDao.insertSearchEntry(searchQuery) }
    }

    @Test
    fun `call the DAO history clearance function on clearSearchHistory call`() = runTest {
        repository.clearSearchHistory()

        // Assert
        verifySuspend(exactly(1)) { searchHistoryDao.clearSearchHistory() }
    }

    @Test
    fun `return flow from DAO on hasSearchHistory call`() = runTest {
        everySuspend { searchHistoryDao.hasSearchHistory() } returns flowOf(true)

        repository.hasSearchHistory().test {
            assertTrue(awaitItem())
            awaitComplete()
        }

        // Assert
        verifySuspend { searchHistoryDao.hasSearchHistory() }
    }

    @Test
    fun `return false on hasSearchHistory call when no history exists in DAO`() = runTest {
        everySuspend { searchHistoryDao.hasSearchHistory() } returns flowOf(false)

        repository.hasSearchHistory().test {
            assertFalse(awaitItem())
            awaitComplete()
        }

        // Assert
        verifySuspend { searchHistoryDao.hasSearchHistory() }
    }
}
