package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.Greeting

interface GreetingRepository {

    suspend fun getGreeting(greetingId: Int): Result<Greeting>
}
