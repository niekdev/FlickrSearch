package dev.niek.flickrsearch.presentation.main

import androidx.lifecycle.ViewModel
import dev.niek.flickrsearch.domain.usecases.GetGreetingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    getGreetingUseCase: GetGreetingUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(
        MainUiState(greeting = getGreetingUseCase().message)
    )
    val state: StateFlow<MainUiState> = _state.asStateFlow()
}
