package dev.niek.flickrsearch.domain.repositories

import app.cash.paging.PagingData
import dev.niek.flickrsearch.domain.models.FlickrImage
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for searching photos using Flickr's API.
 */
interface FlickrRepository {

    /**
     * Searches for photos on Flickr based on a given search term.
     *
     * @param searchTerm The term or keyword used to perform the photo search.
     * @return A [Flow] emitting [PagingData] of [FlickrImage] objects, where each object
     * represents a photo retrieved from Flickr's API.
     */
    fun searchPhotos(searchTerm: String): Flow<PagingData<FlickrImage>>
}
