package dev.niek.flickrsearch.data.datasources.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver = AndroidSqliteDriver(
    FlickrSearchDatabase.Schema,
    androidContext(),
    "FlickrSearchDatabase.db",
)
