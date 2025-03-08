plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.android.application)
}

kotlin {
    jvmToolchain(libs.versions.jdkVersion.get().toInt())

    androidTarget()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.presentation)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.appcompat)
        }
    }
}

android {
    namespace = "dev.niek.flickrsearch.composeapp"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "dev.niek.flickrsearch"
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
