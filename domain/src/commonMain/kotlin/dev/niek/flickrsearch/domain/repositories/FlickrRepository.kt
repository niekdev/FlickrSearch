package dev.niek.flickrsearch.domain.repositories

import app.cash.paging.PagingData
import dev.niek.flickrsearch.domain.models.FlickrImage
import kotlinx.coroutines.flow.Flow

interface FlickrRepository {

    fun searchPhotos(searchTerm: String): Flow<PagingData<FlickrImage>>
}
