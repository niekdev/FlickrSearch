plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    jvmToolchain(libs.versions.jdkVersion.get().toInt())

    androidTarget()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)

            implementation(libs.kotlinx.coroutines)
            implementation(libs.koin.core)
            implementation(libs.paging.common)
            implementation(libs.napier)

            implementation(libs.sqlDelight.primitiveAdapters)
            implementation(libs.sqlDelight.coroutines)

            implementation(libs.ktorCore)
            implementation(libs.ktorClientContentNegotiation)
            implementation(libs.ktorSerialization)
            implementation(libs.ktorResources)
            implementation(libs.ktorClientLogging)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.sqlDelight.androidDriver)
            implementation(libs.ktorClientAndroid)
        }
    }
}

sqldelight {
    databases {
        create("FlickrSearchDatabase") {
            packageName.set("dev.niek.flickrsearch.data.datasources.database")
        }
    }
}

android {
    namespace = "dev.niek.flickrsearch.data"

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
