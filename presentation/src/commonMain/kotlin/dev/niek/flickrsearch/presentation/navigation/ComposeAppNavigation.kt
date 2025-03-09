package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.niek.flickrsearch.presentation.screens.main.MainScreen

@Composable
fun ComposeAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FlickrSearchRoute.Search,
    ) {
        composable<FlickrSearchRoute.Search> {
            MainScreen(navController, FlickrSearchRoute.Search)
        }
        composable<FlickrSearchRoute.History> {
            MainScreen(navController, FlickrSearchRoute.History)
        }
    }
}
