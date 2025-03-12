plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.mokkery)
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

        commonTest.dependencies {
            implementation(libs.junit5)
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.kotlin.test.annotations.common)
            implementation(libs.turbine)
            implementation(libs.paging.test)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.sqlDelight.androidDriver)
            implementation(libs.ktorClientAndroid)
        }

        androidUnitTest.dependencies {
            implementation(libs.sqlDelight.sqliteDriver)
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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}
