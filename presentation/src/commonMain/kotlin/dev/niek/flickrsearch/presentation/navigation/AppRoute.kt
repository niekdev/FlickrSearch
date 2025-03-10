package dev.niek.flickrsearch.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {

    @Serializable
    sealed class MainScreenRoute(val title: String) : AppRoute() {

        @Serializable
        data object Search : MainScreenRoute("Flickr Search")

        @Serializable
        data object History : MainScreenRoute("Search History")
    }

    @Serializable
    data class Results(val searchTerm: String) : AppRoute()
}
