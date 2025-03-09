package dev.niek.flickrsearch.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val route: FlickrSearchRoute,
    val label: String,
    val icon: ImageVector,
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(FlickrSearchRoute.Search, "Search", Icons.Filled.Search),
    BottomNavigationItem(FlickrSearchRoute.History, "History", Icons.Filled.History),
)
