package dev.niek.flickrsearch.domain.usecases

import app.cash.paging.PagingData
import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository
import kotlinx.coroutines.flow.Flow

class SearchFlickrPhotosUseCase(private val flickrRepository: FlickrRepository) {

    operator fun invoke(searchTerm: String): Flow<PagingData<FlickrImage>> =
        flickrRepository.searchPhotos(searchTerm)
}
