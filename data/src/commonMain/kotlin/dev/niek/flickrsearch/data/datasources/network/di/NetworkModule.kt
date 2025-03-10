package dev.niek.flickrsearch.data.datasources.network.di

import dev.niek.flickrsearch.data.datasources.network.createHttpClient
import dev.niek.flickrsearch.data.datasources.network.services.KtorFlickrService
import dev.niek.flickrsearch.domain.datasources.network.services.FlickrService
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean, flickrApiKey: String) -> Module
    get() = { enableLogging, flickrApiKey ->
        module {
            single { createHttpClient(enableLogging) }
            single<FlickrService> { KtorFlickrService(get(), flickrApiKey) }
        }
    }
