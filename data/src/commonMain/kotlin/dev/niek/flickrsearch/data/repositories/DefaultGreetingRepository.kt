package dev.niek.flickrsearch.data.repositories

import dev.niek.flickrsearch.domain.daos.GreetingDao
import dev.niek.flickrsearch.domain.models.Greeting
import dev.niek.flickrsearch.domain.repositories.GreetingRepository

class DefaultGreetingRepository(
    private val greetingDao: GreetingDao,
) : GreetingRepository {

    override suspend fun getGreeting(greetingId: Int): Result<Greeting> {
        return greetingDao.selectGreetingById(greetingId)?.run {
            Result.success(Greeting(message))
        } ?: Result.failure(RuntimeException("Greeting not found"))
    }
}
