package dev.niek.flickrsearch.domain.datasources.network.services

import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse

interface FlickrService {

    suspend fun searchPhotos(searchTerm: String): SearchPhotosResponse
}
