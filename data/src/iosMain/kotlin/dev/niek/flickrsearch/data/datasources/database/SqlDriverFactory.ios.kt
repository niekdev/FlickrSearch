package dev.niek.flickrsearch.data.datasources.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver = NativeSqliteDriver(
    FlickrSearchDatabase.Schema,
    "FlickrSearchDatabase.db",
)
