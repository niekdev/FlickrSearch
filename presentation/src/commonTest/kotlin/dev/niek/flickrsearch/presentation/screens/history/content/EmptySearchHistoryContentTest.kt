package dev.niek.flickrsearch.presentation.screens.history.content

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class EmptySearchHistoryContentTest {

    @Test
    fun emptySearchHistoryContent_displaysCorrectText() = runComposeUiTest {
        setContent {
            EmptySearchHistoryContent()
        }

        // Check title text
        onNodeWithText("No search history yet")
            .assertIsDisplayed()
            .assertHasNoClickAction()

        // Check subtitle text
        onNodeWithText("Your recent searches will appear here")
            .assertIsDisplayed()
            .assertHasNoClickAction()
    }
}
