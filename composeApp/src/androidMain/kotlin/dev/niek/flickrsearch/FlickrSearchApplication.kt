package dev.niek.flickrsearch

import android.app.Application
import dev.niek.flickrsearch.data.datasources.database.di.databaseModule
import dev.niek.flickrsearch.data.datasources.network.di.networkModule
import dev.niek.flickrsearch.data.di.dataModule
import dev.niek.flickrsearch.domain.di.domainModule
import dev.niek.flickrsearch.presentation.di.presentationModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class FlickrSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@FlickrSearchApplication)
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
    }
}
