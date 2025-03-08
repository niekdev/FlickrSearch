package dev.niek.flickrsearch.data.database

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver

fun createDatabase(
    driver: SqlDriver,
): FlickrSearchDatabase = FlickrSearchDatabase(
    driver = driver,
    greetingAdapter = Greeting.Adapter(
        idAdapter = IntColumnAdapter,
    ),
)
