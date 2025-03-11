package dev.niek.flickrsearch.presentation.screens.history

sealed class HistoryUiState {
    data object Loading : HistoryUiState()
    data class HasHistory(val searchHistory: List<String>) : HistoryUiState()
    data object NoHistory : HistoryUiState()
}
