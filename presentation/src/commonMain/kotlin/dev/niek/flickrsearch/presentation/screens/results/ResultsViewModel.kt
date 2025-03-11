package dev.niek.flickrsearch.presentation.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.cachedIn
import app.cash.paging.filter
import dev.niek.flickrsearch.domain.usecases.SearchFlickrPhotosUseCase
import kotlinx.coroutines.flow.map

class ResultsViewModel(
    searchFlickrPhotosUseCase: SearchFlickrPhotosUseCase,
    searchTerm: String,
) : ViewModel() {

    val photosPagingFlow = searchFlickrPhotosUseCase(searchTerm)
        .map { pagingData ->
            // Flickr doesn't guarantee unique photos... apparently. In order to make sure our
            //  LazyColumn doesn't crash because of duplicate keys, we filter out the photo URLs
            //  which we already added.
            val addedUrls = mutableSetOf<String>()
            pagingData.filter { item -> addedUrls.add(item.url) }
        }
        .cachedIn(viewModelScope)
}
