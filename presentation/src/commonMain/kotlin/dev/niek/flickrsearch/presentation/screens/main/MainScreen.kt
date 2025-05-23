package dev.niek.flickrsearch.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.niek.flickrsearch.presentation.navigation.AppRoute
import dev.niek.flickrsearch.presentation.navigation.AppRoute.MainScreenRoute
import dev.niek.flickrsearch.presentation.navigation.bottomNavigationItems
import dev.niek.flickrsearch.presentation.screens.history.HistoryScreen
import dev.niek.flickrsearch.presentation.screens.search.SearchScreen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    currentRoute: MainScreenRoute,
    modifier: Modifier = Modifier,
    vm: MainViewModel = koinViewModel(),
) {
    fun navigateToSearchResults(searchTerm: String) {
        navController.navigate(AppRoute.Results(searchTerm))
    }

    val startDestinationRoute: String = remember {
        requireNotNull(navController.graph.findStartDestination().route)
    }

    var showClearHistoryDialog by remember { mutableStateOf(false) }
    var hasSearchHistory by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentRoute.title)
                },
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .statusBarsPadding(),
                actions = {
                    if (currentRoute != MainScreenRoute.History) return@TopAppBar

                    IconButton(
                        onClick = { showClearHistoryDialog = true },
                        enabled = hasSearchHistory,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Clear search history",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            )
        },
        bottomBar = {
            NavigationBar {
                bottomNavigationItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(startDestinationRoute) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentRoute) {
                MainScreenRoute.Search -> SearchScreen(
                    onClickSearchEntry = { navigateToSearchResults(it) },
                )

                MainScreenRoute.History -> HistoryScreen(
                    onClickSearchEntry = { navigateToSearchResults(it) },
                    hasHistory = { hasSearchHistory = it },
                )
            }
        }
    }

    if (showClearHistoryDialog) {
        AlertDialog(
            title = { Text("Clear Search History") },
            text = { Text("Are you sure you want to delete all your search history? This action cannot be undone.") },
            onDismissRequest = { showClearHistoryDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        vm.clearSearchHistory()
                        showClearHistoryDialog = false
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showClearHistoryDialog = false },
                ) {
                    Text("Cancel")
                }
            },
        )
    }
}
