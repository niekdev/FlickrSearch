plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.plugin) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.buildKonfig) apply false
    alias(libs.plugins.mokkery) apply false
}

tasks.register("runTests") {
    description = "Runs all tests for all modules"
    group = "verification"

    outputs.upToDateWhen { false }

    dependsOn(subprojects.filter { project ->
        project.path != ":presentation"
    }.map { project ->
        project.tasks.matching { task ->
            task is Test && !task.name.contains("release", ignoreCase = true)
        }
    })
}
