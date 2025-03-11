package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.niek.flickrsearch.presentation.screens.main.MainScreen
import dev.niek.flickrsearch.presentation.screens.results.ResultsScreen
import dev.niek.flickrsearch.presentation.theme.FlickrSearchTheme
import org.koin.compose.KoinContext

@Composable
fun ComposeAppNavigation() {
    val navController = rememberNavController()

    KoinContext {
        FlickrSearchTheme {
            NavHost(
                navController = navController,
                startDestination = AppRoute.MainScreenRoute.Search,
            ) {
                composable<AppRoute.MainScreenRoute.Search> {
                    MainScreen(navController, AppRoute.MainScreenRoute.Search)
                }
                composable<AppRoute.MainScreenRoute.History> {
                    MainScreen(navController, AppRoute.MainScreenRoute.History)
                }
                composable<AppRoute.Results> { backStackEntry ->
                    val results: AppRoute.Results = backStackEntry.toRoute()
                    ResultsScreen(navController, searchTerm = results.searchTerm)
                }
            }
        }
    }
}
