package dev.niek.flickrsearch.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.niek.flickrsearch.presentation.navigation.FlickrSearchRoute
import dev.niek.flickrsearch.presentation.navigation.bottomNavigationItems
import dev.niek.flickrsearch.presentation.screens.history.HistoryScreen
import dev.niek.flickrsearch.presentation.screens.search.SearchScreen

@Composable
fun MainScreen(
    navController: NavController,
    currentRoute: FlickrSearchRoute,
    modifier: Modifier = Modifier,
) {
    val startDestinationRoute: String = remember {
        requireNotNull(navController.graph.findStartDestination().route)
    }

    Scaffold(
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
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            when (currentRoute) {
                FlickrSearchRoute.Search -> SearchScreen(navController)
                FlickrSearchRoute.History -> HistoryScreen(navController)
            }
        }
    }
}
