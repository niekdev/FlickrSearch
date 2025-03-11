package dev.niek.flickrsearch

import androidx.compose.ui.window.ComposeUIViewController
import dev.niek.flickrsearch.presentation.navigation.ComposeAppNavigation

@Suppress("Unused") // Is used directly in Swift
fun create() = ComposeUIViewController {
    ComposeAppNavigation()
}
