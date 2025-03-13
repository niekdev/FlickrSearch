package dev.niek.flickrsearch.presentation.screens.history.content

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class SearchHistoryResultsContentTest {

    @Test
    fun searchHistoryResultsContent_displaysAllItems() = runComposeUiTest {
        // Given
        val searchHistory = listOf("Munich", "Ingolstadt", "Dublin")

        // When
        setContent {
            SearchHistoryResultsContent(
                searchHistory = searchHistory,
                onClickSearchEntry = { /* no-op */ },
                modifier = Modifier.testTag("searchHistoryResults"),
            )
        }

        // Then
        onNodeWithTag("searchHistoryResults").assertIsDisplayed()
        searchHistory.forEach { term -> onNodeWithText(term).assertIsDisplayed() }
    }

    @Test
    fun searchHistoryResultsContent_clicksInvokeCallback() = runComposeUiTest {
        // Given
        val searchHistory = listOf("Ingolstadt", "Dublin", "Munich")
        val onClickMock = mock<(String) -> Unit> {
            every { invoke(any()) } returns Unit
        }
        val termToClick = "Dublin"

        // When
        setContent {
            SearchHistoryResultsContent(
                searchHistory = searchHistory,
                onClickSearchEntry = onClickMock,
            )
        }

        // Then
        onNodeWithText(termToClick).performClick()
        verify { onClickMock.invoke(termToClick) }
    }
}
