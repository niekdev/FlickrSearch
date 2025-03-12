package dev.niek.flickrsearch.data.datasources.database

import app.cash.sqldelight.db.SqlDriver

internal expect fun testDbConnection(): SqlDriver
