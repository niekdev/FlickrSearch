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
        startDestination = Routes.SEARCH,
    ) {
        composable(Routes.SEARCH) {
            MainScreen(navController, Routes.SEARCH)
        }
        composable(Routes.HISTORY) {
            MainScreen(navController, Routes.HISTORY)
        }
    }
}
