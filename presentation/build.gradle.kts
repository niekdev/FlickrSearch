plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.android.library)
}

kotlin {
    jvmToolchain(libs.versions.jdkVersion.get().toInt())

    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "FlickrSearchKmp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.kotlinx.serialization)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.paging.common)
            implementation(libs.paging.compose)
            implementation(libs.landscapist)
        }
    }
}

android {
    namespace = "dev.niek.flickrsearch.presentation"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
