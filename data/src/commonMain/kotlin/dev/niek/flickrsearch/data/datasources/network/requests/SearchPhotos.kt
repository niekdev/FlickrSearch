package dev.niek.flickrsearch.data.datasources.network.requests

import io.ktor.resources.Resource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Resource("/services/rest/")
class SearchPhotos(
    @SerialName("method")
    val method: String = "flickr.photos.search",

    @SerialName("api_key")
    val apiKey: String,

    @SerialName("format")
    val format: String = "json",

    @SerialName("nojsoncallback")
    val noJsonCallback: Int = 1,

    @SerialName("safe_search")
    val safeSearch: Int = 1,

    @SerialName("text")
    val searchTerm: String,

    @SerialName("page")
    val page: Int = 1,

    @SerialName("per_page")
    val pageSize: Int = 100,
)
