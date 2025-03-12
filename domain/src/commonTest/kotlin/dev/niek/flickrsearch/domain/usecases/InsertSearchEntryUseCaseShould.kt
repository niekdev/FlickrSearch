package dev.niek.flickrsearch.domain.usecases

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class InsertSearchEntryUseCaseShould {

    private val repository: SearchHistoryRepository = mock<SearchHistoryRepository>() {
        everySuspend { insertSearchEntry(any()) } returns Unit
    }
    private val useCase = InsertSearchEntryUseCase(repository)

    @Test
    fun `call repository with correct search term on invoke`() = runTest {
        val searchTerm = "Munich"

        useCase(searchTerm)

        // Assert
        verifySuspend { repository.insertSearchEntry(searchTerm) }
    }
}
