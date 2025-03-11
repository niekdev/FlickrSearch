package dev.niek.flickrsearch.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import dev.niek.flickrsearch.data.datasources.network.mappers.SearchPhotosResponseToDomainListMapper
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import dev.niek.flickrsearch.domain.models.FlickrImage

private const val FIRST_PAGE = 1 // first page index is 1 in Flickr's API

class FlickrImagePagingSource(
    private val flickrService: FlickrService,
    private val searchPhotosResponseMapper: SearchPhotosResponseToDomainListMapper,
    private val searchTerm: String,
) : PagingSource<Int, FlickrImage>() {

    override suspend fun load(
        params: PagingSourceLoadParams<Int>,
    ): PagingSourceLoadResult<Int, FlickrImage> = runCatching {
        val page = params.key ?: FIRST_PAGE
        val photos = searchPhotosResponseMapper(
            flickrService.searchPhotos(
                searchTerm = searchTerm,
                page = page,
                pageSize = params.loadSize,
            )
        )

        PagingSourceLoadResultPage(
            data = photos,
            prevKey = (page - 1).takeUnless { page == FIRST_PAGE },
            nextKey = (page + 1).takeUnless { photos.isEmpty() },
        )
    }.getOrElse { e -> PagingSourceLoadResultError(e) }

    override fun getRefreshKey(state: PagingState<Int, FlickrImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
