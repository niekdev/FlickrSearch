plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    jvmToolchain(libs.versions.jdkVersion.get().toInt())

    androidTarget()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.domain)

            implementation(libs.koin.core)
            implementation(libs.sqlDelight.primitiveAdapters)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.sqlDelight.androidDriver)
        }
    }
}

sqldelight {
    databases {
        create("FlickrSearchDatabase") {
            packageName.set("dev.niek.flickrsearch.data.database")
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
