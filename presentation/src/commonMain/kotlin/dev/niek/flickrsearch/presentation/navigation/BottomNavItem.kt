package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(Routes.SEARCH, "Search", Icons.Filled.Search),
    BottomNavigationItem(Routes.HISTORY, "History", Icons.Filled.History),
)
