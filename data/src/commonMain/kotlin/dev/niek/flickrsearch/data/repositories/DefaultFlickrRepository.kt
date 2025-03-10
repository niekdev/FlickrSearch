package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository

class DefaultFlickrRepository(
    private val flickrService: FlickrService,
    private val searchPhotosResponseMapper: SearchPhotosResponseToDomainListMapper,
) : FlickrRepository {

    override suspend fun searchPhotos(searchTerm: String): Result<List<FlickrImage>> {
        return runCatching {
            flickrService.searchPhotos(searchTerm)
        }.fold(
            onSuccess = { response -> Result.success(searchPhotosResponseMapper(response)) },
            onFailure = { e -> e.printStackTrace(); Result.failure(e) },
        )
    }
}
