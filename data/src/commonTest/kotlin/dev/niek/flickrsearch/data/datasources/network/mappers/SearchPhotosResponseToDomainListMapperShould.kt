package dev.niek.flickrsearch.data.datasources.network.mappers

import dev.niek.flickrsearch.domain.datasources.network.responses.Photo
import dev.niek.flickrsearch.domain.datasources.network.responses.Photos
import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import kotlin.test.Test

class SearchPhotosResponseToDomainListMapperShould {

    private val mapper = SearchPhotosResponseToDomainListMapper()

    @Test
    fun `map valid photos to FlickrImage list`() {
        val response = SearchPhotosResponse(
            photos = Photos(
                page = 1,
                pages = 10,
                perPage = 10,
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

        val result = mapper(response)

        // Assert
        assertEquals(2, result.size)
        assertEquals("https://farm1.static.flickr.com/s1/111_secretniek.jpg", result[0].url)
        assertEquals("https://farm2.static.flickr.com/s2/222_secretniek.jpg", result[1].url)
    }

    @Test
    fun `filter out photos with invalid server or farm`() {
        val response = SearchPhotosResponse(
            photos = Photos(
                page = 1,
                pages = 10,
                perPage = 10,
                total = 100,
                photo = listOf(
                    Photo(
                        id = "111",
                        owner = "otherniek",
                        secret = "secret1",
                        server = "0",
                        farm = 1,
                        title = "Invalid Server",
                    ),
                    Photo(
                        id = "222",
                        owner = "otherniek",
                        secret = "secret2",
                        server = "s2",
                        farm = 0,
                        title = "Invalid Farm",
                    ),
                    Photo(
                        id = "333",
                        owner = "otherniek",
                        secret = "secret3",
                        server = "s3",
                        farm = 3,
                        title = "Valid Photo",
                    )
                )
            ),
            status = "ok",
        )

        val result = mapper(response)

        // Assert
        assertEquals(1, result.size)
        assertEquals("https://farm3.static.flickr.com/s3/333_secret3.jpg", result[0].url)
    }

    @Test
    fun `return empty list when no valid photos exist`() {
        val response = SearchPhotosResponse(
            photos = Photos(
                page = 1,
                pages = 0,
                perPage = 10,
                total = 0,
                photo = listOf(
                    Photo(
                        id = "111",
                        owner = "anotherniek",
                        secret = "secretniek",
                        server = "0",
                        farm = 0,
                        title = "Invalid Photo",
                    )
                ),
            ),
            status = "ok"
        )

        val result = mapper(response)

        // Assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `return empty list when photos list is empty`() {
        val response = SearchPhotosResponse(
            photos = Photos(
                page = 1,
                pages = 0,
                perPage = 10,
                total = 0,
                photo = emptyList(),
            ),
            status = "ok"
        )

        val result = mapper(response)

        // Assert
        assertTrue(result.isEmpty())
    }
}
