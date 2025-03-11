package dev.niek.flickrsearch

import dev.niek.flickrsearch.data.datasources.database.di.databaseModule
import dev.niek.flickrsearch.data.datasources.network.di.networkModule
import dev.niek.flickrsearch.data.di.dataModule
import dev.niek.flickrsearch.domain.di.domainModule
import dev.niek.flickrsearch.presentation.di.presentationModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

class KoinHelper {

    fun setupNapier() {
        Napier.base(DebugAntilog())
    }

    fun initKoin() = startKoin {
        modules(
            listOf(
                databaseModule,
                networkModule(
                    BuildKonfig.enableRemoteLogging,
                    BuildKonfig.flickrApiKey,
                ),
                presentationModule,
                domainModule,
                dataModule,
            )
        )
    }

    companion object {
        fun initialize() {
            KoinHelper().run {
                setupNapier()
                initKoin()
            }
        }
    }
}

@Suppress("Unused") // Is used directly in Swift
fun initializeHelpers() {
    KoinHelper.initialize()
}
