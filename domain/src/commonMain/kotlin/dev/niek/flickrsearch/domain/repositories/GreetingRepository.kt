package dev.niek.flickrsearch.domain.repositories

import dev.niek.flickrsearch.domain.models.Greeting

interface GreetingRepository {

    fun getGreeting(): Greeting
}
