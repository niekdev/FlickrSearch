package dev.niek.flickrsearch

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

private const val REPOSITORY_ABSTRACTION_SUFFIX = "Repository"

class RepositoryAbstractionsShould {

    private val repositoryAbstractions = Konsist
        .scopeFromProject()
        .interfaces()
        .withNameEndingWith(REPOSITORY_ABSTRACTION_SUFFIX)

    @Test
    fun `be documented`() {
        repositoryAbstractions.assertTrue {
            it.hasKDoc
        }
    }

    @Test
    fun `have all their exposed functions and properties documented`() {
        val exposedFunctions = repositoryAbstractions.functions()
            .filter { it.hasPublicOrDefaultModifier }
        val exposedProperties = repositoryAbstractions.properties()
            .filter { it.hasPublicOrDefaultModifier }

        (exposedProperties + exposedFunctions).assertTrue {
            it.hasKDoc
        }
    }

    @Test
    fun `reside in the 'domain' module`() {
        repositoryAbstractions.assertTrue {
            it.resideInModule("domain")
        }
    }

    @Test
    fun `reside in a 'repositories' package`() {
        repositoryAbstractions.assertTrue {
            it.resideInPackage("..repositories..")
        }
    }
}
