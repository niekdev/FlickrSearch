package dev.niek.flickrsearch.domain.datasources.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPhotosResponse(
    @SerialName("photos")
    val photos: Photos,

    @SerialName("stat")
    val status: String,
)

@Serializable
data class Photos(
    @SerialName("page")
    val page: Int,

    @SerialName("pages")
    val pages: Int,

    @SerialName("perpage")
    val perPage: Int,

    @SerialName("total")
    val total: Int,

    @SerialName("photo")
    val photo: List<Photo>,
)

@Serializable
data class Photo(
    @SerialName("id")
    val id: String,

    @SerialName("owner")
    val owner: String,

    @SerialName("secret")
    val secret: String,

    @SerialName("server")
    val server: String,

    @SerialName("farm")
    val farm: Int,

    @SerialName("title")
    val title: String,

    @SerialName("ispublic")
    val isPublic: Int = 0,

    @SerialName("isfriend")
    val isFriend: Int = 0,

    @SerialName("isfamily")
    val isFamily: Int = 0,
)
