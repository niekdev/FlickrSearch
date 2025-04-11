package dev.niek.flickrsearch.domain.usecases

import app.cash.paging.PagingData
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for searching photos on Flickr based on a given search term.
 *
 * @property flickrRepository An instance of [FlickrRepository] used to perform photo searches.
 */
class SearchFlickrPhotosUseCase(private val flickrRepository: FlickrRepository) {

    operator fun invoke(searchTerm: String): Flow<PagingData<FlickrImage>> =
        flickrRepository.searchPhotos(searchTerm)
}
