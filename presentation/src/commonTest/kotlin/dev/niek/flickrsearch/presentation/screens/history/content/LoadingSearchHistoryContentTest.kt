package dev.niek.flickrsearch.presentation.screens.history.content

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LoadingSearchHistoryContentTest {

    @Test
    fun loadingSearchHistoryContent_displaysProgressIndicator() = runComposeUiTest {
        setContent {
            LoadingSearchHistoryContent(
                modifier = Modifier.testTag("loadingContent")
            )
        }

        // Verify the main container is displayed
        onNodeWithTag("loadingContent")
            .assertIsDisplayed()

        // Verify the CircularProgressIndicator is displayed (first child of the Box)
        onNodeWithTag("loadingContent")
            .onChildAt(0)
            .assertIsDisplayed()
    }
}
