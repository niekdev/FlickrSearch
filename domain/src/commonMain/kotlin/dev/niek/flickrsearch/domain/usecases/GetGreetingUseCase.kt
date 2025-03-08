package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.Greeting
import dev.niek.flickrsearch.domain.repositories.GreetingRepository

class GetGreetingUseCase(private val greetingRepo: GreetingRepository) {

    operator fun invoke(): Greeting = greetingRepo.getGreeting()
}
