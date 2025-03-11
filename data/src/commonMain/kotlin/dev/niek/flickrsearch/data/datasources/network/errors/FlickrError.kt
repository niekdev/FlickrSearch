package dev.niek.flickrsearch.data.datasources.network.errors

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException

enum class FlickrError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError,
}

class FlickrException(error: FlickrError) : Exception(
    "Something went wrong: $error"
)

suspend inline fun <reified T> handleErrors(
    crossinline response: suspend () -> HttpResponse,
): T {
    val result = try {
        response()
    } catch (e: IOException) {
        throw FlickrException(FlickrError.ServiceUnavailable)
    }

    when (result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw FlickrException(FlickrError.ClientError)
        500 -> throw FlickrException(FlickrError.ServerError)
        else -> throw FlickrException(FlickrError.UnknownError)
    }

    return try {
        result.body()
    } catch (e: Exception) {
        throw FlickrException(FlickrError.ServerError)
    }
}
