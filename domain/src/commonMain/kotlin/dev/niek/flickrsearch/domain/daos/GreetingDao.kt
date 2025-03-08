package dev.niek.flickrsearch.domain.daos

import dev.niek.flickrsearch.domain.models.Greeting

interface GreetingDao {

    suspend fun selectGreetingById(greetingId: Int): Greeting?
}
