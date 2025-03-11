package dev.niek.flickrsearch.presentation.screens.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.niek.flickrsearch.presentation.navigation.AppRoute
import dev.niek.flickrsearch.presentation.screens.history.content.EmptySearchHistoryContent
import dev.niek.flickrsearch.presentation.screens.history.content.LoadingSearchHistoryContent
import dev.niek.flickrsearch.presentation.screens.history.content.SearchHistoryResultsContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm: HistoryViewModel = koinViewModel(),
) {
    val uiState by vm.state.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HistoryUiState.Loading -> {
            LoadingSearchHistoryContent(
                modifier = modifier,
            )
        }

        is HistoryUiState.HasHistory -> {
            SearchHistoryResultsContent(
                searchHistory = state.searchHistory,
                onClickSearchEntry = {
                    navController.navigate(AppRoute.Results(searchTerm = it))
                },
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
