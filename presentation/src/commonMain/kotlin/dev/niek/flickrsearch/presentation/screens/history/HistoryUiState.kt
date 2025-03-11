package dev.niek.flickrsearch.presentation.screens.history

sealed class HistoryUiState(val hasHistory: Boolean) {
    data object Loading : HistoryUiState(false)
    data class HasHistory(val searchHistory: List<String>) : HistoryUiState(true)
    data object NoHistory : HistoryUiState(false)
}
