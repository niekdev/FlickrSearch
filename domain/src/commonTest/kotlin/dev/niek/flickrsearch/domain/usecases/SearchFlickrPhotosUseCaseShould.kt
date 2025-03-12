package dev.niek.flickrsearch.domain.usecases

import app.cash.paging.PagingData
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchFlickrPhotosUseCaseShould {

    private val repository: FlickrRepository = mock<FlickrRepository>()
    private val useCase = SearchFlickrPhotosUseCase(repository)

    @Test
    fun `return correct flow from repository when searching photos`() = runTest {
        val searchTerm = "Ingolstadt"
        val mockFlow = flowOf(
            PagingData.from(
                listOf(
                    FlickrImage("https://niek.dev/image1"),
                    FlickrImage("https://niek.dev/image2"),
                )
            )
        )
        every { repository.searchPhotos(searchTerm) } returns mockFlow

        val resultFlow = useCase(searchTerm)

        // Assert
        assertEquals(mockFlow, resultFlow)
    }

    @Test
    fun `pass correct search term to repository when searching photos`() = runTest {
        val searchTerm = "Dublin"
        every { repository.searchPhotos(any()) } returns flowOf(PagingData.empty())

        useCase(searchTerm)

        // Assert
        verify { repository.searchPhotos(searchTerm) }
    }
}
