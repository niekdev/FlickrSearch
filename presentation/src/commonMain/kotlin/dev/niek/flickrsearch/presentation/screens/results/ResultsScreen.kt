package dev.niek.flickrsearch.presentation.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import dev.niek.flickrsearch.presentation.screens.results.content.FlickrPhotosErrorContent
import dev.niek.flickrsearch.presentation.screens.results.content.HasFlickrPhotosContent
import dev.niek.flickrsearch.presentation.screens.results.content.LoadingFlickrPhotosContent
import dev.niek.flickrsearch.presentation.screens.results.content.NoFlickrPhotosFoundContent
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    navController: NavController,
    searchTerm: String,
    modifier: Modifier = Modifier,
    vm: ResultsViewModel = koinViewModel(),
) {
    val uiState by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.doSearch(searchTerm)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(searchTerm) },
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .statusBarsPadding(),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is ResultsUiState.Loading -> LoadingFlickrPhotosContent(
                modifier = Modifier.padding(innerPadding),
            )

            is ResultsUiState.HasPhotos -> HasFlickrPhotosContent(
                imageUrls = state.imageUrls,
                modifier = Modifier.padding(innerPadding),
            )

            is ResultsUiState.NoPhotos -> NoFlickrPhotosFoundContent(
                modifier = Modifier.padding(innerPadding),
            )

            is ResultsUiState.Error -> FlickrPhotosErrorContent(
                errorMessage = state.message,
                onClickTryAgain = { /* no-op */ },
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
