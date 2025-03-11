package dev.niek.flickrsearch.presentation.screens.results.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.PagingData
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import dev.niek.flickrsearch.domain.models.FlickrImage
import kotlinx.coroutines.flow.Flow

@Composable
fun FlickrPhotosContent(
    imageUrls: Flow<PagingData<FlickrImage>>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val pagingItems = imageUrls.collectAsLazyPagingItems()

    val loadingIndicator: @Composable LazyItemScope.() -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp,
            )
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { item -> item.url },
        ) { index ->
            pagingItems[index]?.run {
                CoilImage(
                    imageModel = { url },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f)
                        .background(MaterialTheme.colorScheme.surfaceContainer),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                    ),
                )
            } ?: run {
                loadingIndicator()
            }
        }

        when {
            pagingItems.loadState.refresh is LoadStateLoading -> {
                item { loadingIndicator() }
            }

            pagingItems.loadState.append is LoadStateLoading -> {
                item { loadingIndicator() }
            }

            pagingItems.loadState.refresh is LoadStateError -> {
                val error = pagingItems.loadState.refresh as LoadStateError
                item { Text(text = error.error.message ?: "Error occurred") }
            }
        }
    }
}
