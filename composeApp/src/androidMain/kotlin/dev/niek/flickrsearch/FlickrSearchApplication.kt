package dev.niek.flickrsearch

import android.app.Application
import dev.niek.flickrsearch.data.di.dataModule
import dev.niek.flickrsearch.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class FlickrSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@FlickrSearchApplication)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                )
            )
        }
    }
}
