package dev.niek.flickrsearch.presentation.screens.history.content

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchHistoryResultsContent(
    searchHistory: List<String>,
    onClickSearchEntry: (searchTerm: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = spacedBy(16.dp),
    ) {
        items(
            items = searchHistory,
            itemContent = { item ->
                Button(
                    onClick = {
                        onClickSearchEntry(item)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(item)
                }
            }
        )
    }
}