package dev.niek.flickrsearch.presentation.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.SearchFlickrPhotosUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResultsViewModel(
    private val searchFlickrPhotosUseCase: SearchFlickrPhotosUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _state = MutableStateFlow<ResultsUiState>(ResultsUiState.Loading)
    val state: StateFlow<ResultsUiState> = _state.asStateFlow()

    fun doSearch(searchQuery: String) {
        viewModelScope.launch(dispatcher) {
            searchFlickrPhotosUseCase(searchQuery).fold(
                onSuccess = { result ->
                    _state.update {
                        val imageUrls = result.map { it.url }

                        if (imageUrls.isNotEmpty()) {
                            ResultsUiState.HasPhotos(imageUrls)
                        } else {
                            ResultsUiState.NoPhotos
                        }
                    }
                },
                onFailure = { e ->
                    e.printStackTrace()
                    _state.update {
                        ResultsUiState.Error(e.message ?: "Unknown error")
                    }
                }
            )
        }
    }
}
