package dev.niek.flickrsearch.presentation.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.niek.flickrsearch.presentation.screens.history.content.EmptySearchHistoryContent
import dev.niek.flickrsearch.presentation.screens.history.content.LoadingSearchHistoryContent
import dev.niek.flickrsearch.presentation.screens.history.content.SearchHistoryResultsContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(
    onClickSearchEntry: (searchTerm: String) -> Unit,
    hasHistory: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    vm: HistoryViewModel = koinViewModel(),
) {
    val uiState by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.hasHistory) {
        hasHistory(uiState.hasHistory)
    }

    when (val state = uiState) {
        is HistoryUiState.Loading -> {
            LoadingSearchHistoryContent(
                modifier = modifier,
            )
        }

        is HistoryUiState.HasHistory -> {
            SearchHistoryResultsContent(
                searchHistory = state.searchHistory,
                onClickSearchEntry = onClickSearchEntry,
                modifier = modifier,
            )
        }

        is HistoryUiState.NoHistory -> {
            EmptySearchHistoryContent(
                modifier = modifier,
            )
        }
    }
}
