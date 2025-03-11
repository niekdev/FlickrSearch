package dev.niek.flickrsearch.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.GetSearchHistoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class) // Required for 'mapLatest'
    val state: StateFlow<HistoryUiState> =
        getSearchHistoryUseCase().mapLatest { history ->
            if (history.isNotEmpty()) {
                HistoryUiState.HasHistory(history.map { entry -> entry.query })
            } else {
                HistoryUiState.NoHistory
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            HistoryUiState.Loading,
        )
}
