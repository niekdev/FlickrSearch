package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.models.Greeting
import dev.niek.flickrsearch.domain.repositories.GreetingRepository

class DefaultGreetingRepository : GreetingRepository {

    override fun getGreeting(): Greeting = Greeting("Hello World!")
}
