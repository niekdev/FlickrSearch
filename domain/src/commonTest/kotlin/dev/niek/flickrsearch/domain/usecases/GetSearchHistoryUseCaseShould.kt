package dev.niek.flickrsearch.domain.usecases

import app.cash.turbine.test
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import dev.niek.flickrsearch.domain.models.SearchEntry
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSearchHistoryUseCaseShould {

    private val repository: SearchHistoryRepository = mock<SearchHistoryRepository>()
    private val useCase = GetSearchHistoryUseCase(repository)

    @Test
    fun `return correct flow from repository on invoke`() = runTest {
        every { repository.getSearchHistory() } returns flowOf(
            listOf(
                SearchEntry("Ingolstadt"),
                SearchEntry("Dublin"),
            )
        )

        useCase().test {
            val result = awaitItem()
            assertEquals(2, result.size)
            assertEquals("Ingolstadt", result[0].query)
            assertEquals("Dublin", result[1].query)
            awaitComplete()
        }

        // Assert
        verify { repository.getSearchHistory() }
    }
}
