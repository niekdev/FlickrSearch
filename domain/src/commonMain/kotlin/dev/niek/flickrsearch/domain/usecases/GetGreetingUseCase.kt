package dev.niek.flickrsearch.domain.usecases

import dev.niek.flickrsearch.domain.models.Greeting
import dev.niek.flickrsearch.domain.repositories.GreetingRepository

class GetGreetingUseCase(private val greetingRepo: GreetingRepository) {

    suspend operator fun invoke(): Result<Greeting> = greetingRepo.getGreeting(0)
}
