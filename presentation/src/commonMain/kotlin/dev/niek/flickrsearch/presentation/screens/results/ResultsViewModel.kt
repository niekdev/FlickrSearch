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

    private val _state = MutableStateFlow(ResultsUiState())
    val state: StateFlow<ResultsUiState> = _state.asStateFlow()

    fun doSearch(searchQuery: String) {
        viewModelScope.launch(dispatcher) {
            searchFlickrPhotosUseCase(searchQuery).onSuccess { result ->
                _state.update { state -> state.copy(imageUrls = result.map { it.url }) }
            }
        }
    }
}
