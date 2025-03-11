package dev.niek.flickrsearch.data.datasources.network

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient(
    enableLogging: Boolean,
): HttpClient = HttpClient {
    install(DefaultRequest) {
        url("https://api.flickr.com")

        header("accept", "application/json")
    }

    install(Resources)

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }

    if (enableLogging) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(message = message, tag = "HttpClient")
                }
            }
            level = LogLevel.ALL
        }
    }
}
