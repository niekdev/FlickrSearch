package dev.niek.flickrsearch.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface FlickrSearchRoute {

    @Serializable
    data object Search : FlickrSearchRoute

    @Serializable
    data object History : FlickrSearchRoute
}
