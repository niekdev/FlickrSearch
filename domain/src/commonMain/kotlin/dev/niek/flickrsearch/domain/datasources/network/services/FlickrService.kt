package dev.niek.flickrsearch.domain.datasources.network.services

import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse

interface FlickrService {

    suspend fun searchPhotos(
        searchTerm: String,
        page: Int = 1,
        pageSize: Int = 100, // 100 Seems to be the default on Flickr
    ): SearchPhotosResponse
}
