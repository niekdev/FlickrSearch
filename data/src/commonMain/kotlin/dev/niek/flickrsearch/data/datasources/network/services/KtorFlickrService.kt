package dev.niek.flickrsearch.data.datasources.network.services

import dev.niek.flickrsearch.data.datasources.network.errors.handleErrors
import dev.niek.flickrsearch.data.datasources.network.requests.SearchPhotos
import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.resources.get

class KtorFlickrService(
    private val httpClient: HttpClient,
    private val flickrApiKey: String,
) : FlickrService {

    override suspend fun searchPhotos(
        searchTerm: String,
        page: Int,
        pageSize: Int,
    ): SearchPhotosResponse = handleErrors {
        val request = SearchPhotos(
            apiKey = flickrApiKey,
            searchTerm = searchTerm,
            page = page,
            pageSize = pageSize,
        )
        httpClient.get(request)
    }
}
