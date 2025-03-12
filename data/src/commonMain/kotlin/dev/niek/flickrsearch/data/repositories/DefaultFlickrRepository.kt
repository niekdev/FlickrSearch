package dev.niek.flickrsearch.data.repositories

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.PagingSource
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.data.paging.FlickrImagePagingSource
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.Flow

class DefaultFlickrRepository(
    private val flickrService: FlickrService,
    private val searchPhotosResponseMapper: SearchPhotosResponseToDomainListMapper,
    private val pagingSourceFactory: (String) -> PagingSource<Int, FlickrImage> = { term ->
        FlickrImagePagingSource(flickrService, searchPhotosResponseMapper, term)
    },
) : FlickrRepository {

    override fun searchPhotos(searchTerm: String): Flow<PagingData<FlickrImage>> = Pager(
        config = PagingConfig(
            pageSize = 50,
            initialLoadSize = 50,
            prefetchDistance = 20,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { pagingSourceFactory(searchTerm) },
    ).flow
}
