package dev.niek.flickrsearch.domain.usecases

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import dev.niek.flickrsearch.domain.repositories.SearchHistoryRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ClearSearchHistoryUseCaseShould {

    private val repository: SearchHistoryRepository = mock<SearchHistoryRepository> {
        everySuspend { clearSearchHistory() } returns Unit
    }
    private val useCase = ClearSearchHistoryUseCase(repository)

    @Test
    fun `call repository 'clearSearchHistory' fun exactly once on single invoke`() = runTest {
        useCase()

        // Assert
        verifySuspend(exactly(1)) { repository.clearSearchHistory() }
    }

    @Test
    fun `call repository 'clearSearchHistory' fun exactly thrice on triple invoke`() = runTest {
        useCase()
        useCase()
        useCase()

        // Assert
        verifySuspend(exactly(3)) { repository.clearSearchHistory() }
    }
}
