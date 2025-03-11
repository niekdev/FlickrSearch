package dev.niek.flickrsearch.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.niek.flickrsearch.domain.usecases.ClearSearchHistoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class MainViewModel(
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    fun clearSearchHistory() {
        viewModelScope.launch(dispatcher) {
            clearSearchHistoryUseCase()
        }
    }
}
