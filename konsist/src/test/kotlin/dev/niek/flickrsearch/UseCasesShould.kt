package dev.niek.flickrsearch

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

private const val USE_CASE_SUFFIX = "UseCase"

class UseCasesShould {

    private val useCases = Konsist
        .scopeFromProject()
        .classes()
        .withNameEndingWith(USE_CASE_SUFFIX)

    @Test
    fun `be documented`() {
        useCases.assertTrue {
            it.hasKDoc
        }
    }

    @Test
    fun `reside in the 'domain' module`() {
        useCases.assertTrue {
            it.resideInModule("domain")
        }
    }

    @Test
    fun `reside in a 'usecases' package`() {
        useCases.assertTrue {
            it.resideInPackage("..usecases..")
        }
    }

    @Test
    fun `have a test class with the name ending with 'Should'`() {
        useCases.assertTrue {
            it.hasTestClass { test -> test.name.endsWith("Should") }
        }
    }

    @Test
    fun `have an 'invoke' operator function with a public or default modifier`() {
        fun KoFunctionDeclaration.hasExposedInvokeOperatorFunction(): Boolean =
            name == "invoke" && hasOperatorModifier && hasPublicOrDefaultModifier

        useCases.assertTrue {
            it.hasFunction { func -> func.hasExposedInvokeOperatorFunction() }
        }
    }

    @Test
    fun `not expose more than one function`() {
        useCases.assertTrue {
            it.countFunctions { func -> func.hasPublicOrDefaultModifier } == 1
        }
    }

    @Test
    fun `not expose any properties`() {
        useCases.assertTrue {
            it.countProperties { prop -> prop.hasPublicOrDefaultModifier } == 0
        }
    }
}
