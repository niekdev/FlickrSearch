package dev.niek.flickrsearch.data.paging

import app.cash.paging.PagingConfig
import app.cash.paging.PagingSourceLoadParamsRefresh
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.network.responses.Photo
import dev.niek.flickrsearch.domain.datasources.network.responses.Photos
import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import kotlinx.coroutines.test.runTest
import kotlinx.io.IOException
import org.junit.Assert.assertEquals
import org.junit.Test

private const val searchTerm = "Munich"
private const val pageSize = 20

private val mockResponse = SearchPhotosResponse(
    photos = Photos(
        page = 1,
        pages = 10,
        perPage = pageSize,
        total = 100,
        photo = listOf(
            Photo(
                id = "111",
                owner = "niek",
                secret = "secretniek",
                server = "s1",
                farm = 1,
                title = "Photo 1",
            ),
            Photo(
                id = "222",
                owner = "niek",
                secret = "secretniek",
                server = "s2",
                farm = 2,
                title = "Photo 2",
            )
        )
    ),
    status = "ok",
)

private val mockEmptyResponse = SearchPhotosResponse(
    photos = Photos(
        page = 1,
        pages = 0,
        perPage = pageSize,
        total = 0,
        photo = emptyList(),
    ),
    status = "ok",
)

class FlickrImagePagingSourceShould {

    private val flickrService: FlickrService = mock<FlickrService>()
    private val mapper = SearchPhotosResponseToDomainListMapper()
    private val pagingSource = FlickrImagePagingSource(flickrService, mapper, searchTerm)

    private val expectedImages = listOf(
        FlickrImage("https://farm1.static.flickr.com/s1/111_secretniek.jpg", "Photo 1"),
        FlickrImage("https://farm2.static.flickr.com/s2/222_secretniek.jpg", "Photo 2"),
    )

    @Test
    fun `return success result when API call succeeds`() = runTest {
        // Given
        val page = 1

        everySuspend {
            flickrService.searchPhotos(
                searchTerm = searchTerm,
                page = page,
                pageSize = pageSize,
            )
        } returns mockResponse

        // When
        val result = pagingSource.load(
            PagingSourceLoadParamsRefresh(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        // Then
        val expected = PagingSourceLoadResultPage(
            data = expectedImages,
            prevKey = null,
            nextKey = page + 1,
        )

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `return success result with correct prev and next keys for middle page`() = runTest {
        // Given
        val page = 2

        everySuspend {
            flickrService.searchPhotos(
                searchTerm = searchTerm,
                page = page,
                pageSize = pageSize,
            )
        } returns mockResponse

        // When
        val result = pagingSource.load(
            PagingSourceLoadParamsRefresh(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        // Then
        val expected = PagingSourceLoadResultPage(
            data = expectedImages,
            prevKey = page - 1,
            nextKey = page + 1,
        )

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `return success result with null nextKey when response is empty`() = runTest {
        // Given
        val page = 3

        everySuspend {
            flickrService.searchPhotos(
                searchTerm = searchTerm,
                page = page,
                pageSize = pageSize,
            )
        } returns mockEmptyResponse

        // When
        val result = pagingSource.load(
            PagingSourceLoadParamsRefresh(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        // Then
        val expected = PagingSourceLoadResultPage(
            data = emptyList<FlickrImage>(),
            prevKey = page - 1,
            nextKey = null,
        )

        // Assert
        assertEquals(expected, result)
    }

    @Test
    fun `return error result when API call fails`() = runTest {
        // Given
        val page = 1
        val exception = IOException("Network error")

        everySuspend {
            flickrService.searchPhotos(
                searchTerm = searchTerm,
                page = page,
                pageSize = pageSize,
            )
        } throws exception

        // When
        val result = pagingSource.load(
            PagingSourceLoadParamsRefresh(
                key = page,
                loadSize = pageSize,
                placeholdersEnabled = false,
            )
        )

        // Then
        val expected = PagingSourceLoadResultError<Int, FlickrImage>(exception)

        // Assert
        assertEquals(expected.toString(), result.toString())
    }

    @Test
    fun `return null for getRefreshKey when anchorPosition is null`() {
        val state = PagingState<Int, FlickrImage>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 0,
        )

        val refreshKey = pagingSource.getRefreshKey(state)

        // Assert
        assertEquals(null, refreshKey)
    }

    @Test
    fun `return correct key for getRefreshKey based on prev and next keys`() {
        // Given
        val pages = listOf(
            PagingSourceLoadResultPage(
                data = expectedImages,
                prevKey = 2,
                nextKey = 4,
            )
        )

        val state = PagingState(
            pages = pages,
            anchorPosition = 0, // Position of the first item in our list
            config = PagingConfig(pageSize = pageSize),
            leadingPlaceholderCount = 0,
        )

        // When
        val refreshKey = pagingSource.getRefreshKey(state)

        // Assert
        assertEquals(3, refreshKey) // prevKey + 1
    }

    @Test
    fun `return correct key for getRefreshKey when prevKey is null`() {
        // Given
        val mockPage = PagingSourceLoadResultPage(
            data = expectedImages,
            prevKey = null,
            nextKey = 4,
        )

        val state = PagingState(
            pages = listOf(mockPage),
            anchorPosition = 10,
            config = PagingConfig(pageSize = pageSize),
            leadingPlaceholderCount = 0,
        )

        // When
        val refreshKey = pagingSource.getRefreshKey(state)

        // Assert
        assertEquals(3, refreshKey) // nextKey - 1
    }
}
