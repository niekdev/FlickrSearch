package dev.niek.flickrsearch.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.GetSearchEntryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    getSearchEntryUseCase: GetSearchEntryUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state: StateFlow<MainUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            _state.update {
                getSearchEntryUseCase().fold(
                    onSuccess = {
                        MainUiState(it.query)
                    },
                    onFailure = {
                        MainUiState()
                    }
                )
            }
        }
    }
}
