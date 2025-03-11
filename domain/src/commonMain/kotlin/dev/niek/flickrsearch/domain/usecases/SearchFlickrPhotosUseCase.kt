package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.FlickrImage
import dev.niek.flickrsearch.domain.repositories.FlickrRepository

class SearchFlickrPhotosUseCase(private val flickrRepository: FlickrRepository) {

    suspend operator fun invoke(searchTerm: String): Result<List<FlickrImage>> =
        flickrRepository.searchPhotos(searchTerm)
}
