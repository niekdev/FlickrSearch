package dev.niek.flickrsearch.data.datasources.network.mappers

import dev.niek.flickrsearch.domain.datasources.network.responses.Photo
import dev.niek.flickrsearch.domain.datasources.network.responses.SearchPhotosResponse
import dev.niek.flickrsearch.domain.models.FlickrImage

class SearchPhotosResponseToDomainListMapper : Function1<SearchPhotosResponse, List<FlickrImage>> {

    override fun invoke(response: SearchPhotosResponse): List<FlickrImage> {
        fun Photo.buildUrl(): String? =
            "https://farm$farm.static.flickr.com/$server/$`id`_$secret.jpg".takeUnless {
                server == "0" || farm == 0
            }

        return response.photos.photo.mapNotNull { photo ->
            photo.buildUrl()?.run { FlickrImage(this) }
        }
    }
}
