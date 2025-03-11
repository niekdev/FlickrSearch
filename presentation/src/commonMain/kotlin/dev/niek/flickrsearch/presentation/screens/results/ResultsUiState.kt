package dev.niek.flickrsearch.presentation.screens.results

sealed class ResultsUiState {
    data object Loading : ResultsUiState()
    data class HasPhotos(val imageUrls: List<String>) : ResultsUiState()
    data object NoPhotos : ResultsUiState()
    data class Error(val message: String) : ResultsUiState()
}
