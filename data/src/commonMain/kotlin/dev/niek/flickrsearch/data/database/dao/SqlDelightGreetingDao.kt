package dev.niek.flickrsearch.data.database.dao

import dev.niek.flickrsearch.data.database.FlickrSearchDatabase
import dev.niek.flickrsearch.data.database.mappers.GreetingEntityToDomainMapper
import dev.niek.flickrsearch.domain.daos.GreetingDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SqlDelightGreetingDao(
    private val database: FlickrSearchDatabase,
    private val greetingEntityToDomainMapper: GreetingEntityToDomainMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GreetingDao {

    private val query get() = database.greetingQueries

    override suspend fun selectGreetingById(greetingId: Int) = withContext(dispatcher) {
        query.selectGreetingById(id = greetingId)
            .executeAsOneOrNull()
            ?.run(greetingEntityToDomainMapper)
    }
}
