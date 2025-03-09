package dev.niek.flickrsearch.presentation.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.GetSearchHistoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel(
    getSearchHistoryUseCase: GetSearchHistoryUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryUiState(emptyList()))
    val state: StateFlow<HistoryUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            getSearchHistoryUseCase()
                .flowOn(dispatcher)
                .collectLatest { history ->
                    _state.update {
                        it.copy(searchHistory = history.map { entry -> entry.query })
                    }
                }
        }
    }
}
