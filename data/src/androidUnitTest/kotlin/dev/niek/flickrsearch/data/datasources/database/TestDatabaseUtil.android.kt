package dev.niek.flickrsearch.data.datasources.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

internal actual fun testDbConnection(): SqlDriver = JdbcSqliteDriver(
    JdbcSqliteDriver.IN_MEMORY,
).also { FlickrSearchDatabase.Schema.create(it) }
