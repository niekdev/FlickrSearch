package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.niek.flickrsearch.presentation.screens.main.MainScreen
import dev.niek.flickrsearch.presentation.screens.results.ResultsScreen
import dev.niek.flickrsearch.presentation.theme.FlickrSearchTheme
import org.koin.compose.KoinContext

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ComposeAppNavigation() {
    val navController = rememberNavController()

    KoinContext {
        FlickrSearchTheme {
            SharedTransitionLayout {
                NavHost(
                    navController = navController,
                    startDestination = AppRoute.MainScreenRoute.Search,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
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
}
