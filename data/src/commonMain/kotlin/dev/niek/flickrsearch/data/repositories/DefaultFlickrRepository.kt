package dev.niek.flickrsearch.data.repositories

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.data.paging.FlickrImagePagingSource
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.Flow

class DefaultFlickrRepository(
    private val flickrService: FlickrService,
    private val searchPhotosResponseMapper: SearchPhotosResponseToDomainListMapper,
) : FlickrRepository {

    override fun searchPhotos(searchTerm: String): Flow<PagingData<FlickrImage>> = Pager(
        config = PagingConfig(
            pageSize = 50,
            initialLoadSize = 50,
            prefetchDistance = 20,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            FlickrImagePagingSource(flickrService, searchPhotosResponseMapper, searchTerm)
        },
    ).flow
}
