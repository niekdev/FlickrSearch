package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.FlickrImage

interface FlickrRepository {

    suspend fun searchPhotos(searchTerm: String): Result<List<FlickrImage>>
}
