package dev.niek.flickrsearch.presentation.screens.history

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HistoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    vm: HistoryViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp),
    ) {
        items(
            items = state.searchHistory,
            itemContent = { item ->
                Button(
                    onClick = { /* no-op */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(item)
                }
            }
        )
    }
}
