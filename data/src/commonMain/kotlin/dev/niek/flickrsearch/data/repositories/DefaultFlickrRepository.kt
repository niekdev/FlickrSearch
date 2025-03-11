package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository

class DefaultFlickrRepository(
    private val flickrService: FlickrService,
) : FlickrRepository {

    override suspend fun searchPhotos(searchTerm: String): Result<List<FlickrImage>> {
        return runCatching {
            flickrService.searchPhotos(searchTerm)
        }.fold(
            onSuccess = { response ->
                val flickrImages = response.photos.photo
                    // TODO: Use an injected mapper which takes care of extracting the URL
                    .map { FlickrImage(it.title) }

                Result.success(flickrImages)
            },
            onFailure = { e -> e.printStackTrace(); Result.failure(e) },
        )
    }
}
