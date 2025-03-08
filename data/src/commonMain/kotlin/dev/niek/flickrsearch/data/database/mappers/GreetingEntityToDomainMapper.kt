package dev.niek.flickrsearch.data.database.mappers

import dev.niek.flickrsearch.data.database.Greeting as GreetingEntity
import dev.niek.flickrsearch.domain.models.Greeting as GreetingDomain

class GreetingEntityToDomainMapper : Function1<GreetingEntity, GreetingDomain> {

    override fun invoke(greeting: GreetingEntity): GreetingDomain {
        return GreetingDomain(message = greeting.message)
    }
}
