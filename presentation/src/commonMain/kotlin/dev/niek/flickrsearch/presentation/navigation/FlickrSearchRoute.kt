package dev.niek.flickrsearch.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class FlickrSearchRoute(val title: String) {

    @Serializable
    data object Search : FlickrSearchRoute("Flickr Search")

    @Serializable
    data object History : FlickrSearchRoute("Search History")
}
