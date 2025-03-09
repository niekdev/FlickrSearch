package dev.niek.flickrsearch.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.ClearSearchHistoryUseCase
import dev.niek.flickrsearch.domain.usecases.HasSearchHistoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase,
    private val hasSearchHistoryUseCase: HasSearchHistoryUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    init {
        observeSearchHistory()
    }

    fun clearSearchHistory() {
        viewModelScope.launch(dispatcher) {
            clearSearchHistoryUseCase()
        }
    }

    private fun observeSearchHistory() {
        viewModelScope.launch(dispatcher) {
            hasSearchHistoryUseCase().collectLatest { hasHistory ->
                _state.update { it.copy(hasSearchHistory = hasHistory) }
            }
        }
    }
}
