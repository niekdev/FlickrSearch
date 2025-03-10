package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import dev.niek.flickrsearch.presentation.navigation.AppRoute.MainScreenRoute

data class BottomNavigationItem(
    val route: MainScreenRoute,
    val label: String,
    val icon: ImageVector,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(MainScreenRoute.Search, "Search", Icons.Filled.Search),
    BottomNavigationItem(MainScreenRoute.History, "History", Icons.Filled.History),
)
