package dev.niek.flickrsearch.data.repositories

import app.cash.paging.PagingData
import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import app.cash.paging.testing.asSnapshot
import dev.mokkery.mock
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * The PagingSource class is closed.
 */
private class TestPagingSource<T : Any>(private val data: List<T>) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> = LoadResult.Page(
        data = data,
        prevKey = null,
        nextKey = null,
    )

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
}

class DefaultFlickrRepositoryShould {

    private val mockImages = listOf(
        FlickrImage("https://niek.dev/image1"),
        FlickrImage("https://niek.dev/image2"),
    )

    private val flickrService: FlickrService = mock<FlickrService>()
    private val mapper = SearchPhotosResponseToDomainListMapper()
    private val testPagingSource = TestPagingSource(mockImages)

    /**
     * Small convenience function to initialize a DefaultFlickrRepository.
     */
    private fun getRepositoryWithPagingSource(
        pagingSource: (String) -> PagingSource<Int, FlickrImage>,
    ): FlickrRepository = DefaultFlickrRepository(
        flickrService,
        mapper,
        pagingSourceFactory = pagingSource,
    )

    @Test
    fun `return a PagingData flow of type FlickrImage on searchPhotos call`() = runTest {
        val searchTerm = "Ingolstadt"
        val repository = getRepositoryWithPagingSource { testPagingSource }

        val result = repository.searchPhotos(searchTerm)

        // Assert
        assertTrue(result.first() is PagingData<FlickrImage>)
    }

    @Test
    fun `use provided paging source factory on searchPhotos call`() = runTest {
        val searchTerm = "Dublin"
        var factoryInvoked = false
        val repository = getRepositoryWithPagingSource { term ->
            factoryInvoked = true
            assertEquals(searchTerm, term)
            testPagingSource
        }

        repository.searchPhotos(searchTerm).first() // Collect once to trigger factory

        // Assert
        assertTrue(factoryInvoked)
    }

    @Test
    fun `return expected data on searchPhotos call`() = runTest {
        val searchTerm = "Munich"
        val repository = getRepositoryWithPagingSource { testPagingSource }

        val snapshot = repository.searchPhotos(searchTerm).asSnapshot {
            appendScrollWhile { true } // scroll to actually force some data
        }

        // Assert
        assertEquals(mockImages, snapshot)
    }

    @Test
    fun `create different paging sources when calling searchPhotos with another term`() = runTest {
        val searchTerm1 = "Dublin"
        val searchTerm2 = "Ingolstadt"
        var factoryInvocations = 0
        val repository = getRepositoryWithPagingSource { _ ->
            factoryInvocations++
            TestPagingSource(mockImages)
        }

        repository.searchPhotos(searchTerm1).first()
        repository.searchPhotos(searchTerm2).first()

        // Assert
        assertEquals(2, factoryInvocations)
    }
}
