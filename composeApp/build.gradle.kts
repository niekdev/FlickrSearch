import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.io.FileInputStream
import java.util.Properties

buildscript {
    dependencies {
        classpath(libs.buildKonfig)
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.plugin)
    alias(libs.plugins.android.application)
    alias(libs.plugins.buildKonfig)
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
            implementation(projects.presentation)
            implementation(projects.domain)
            implementation(projects.data)
            implementation(libs.napier)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.appcompat)
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            implementation(libs.sqlDelight.nativeDriver)
        }
    }
}

android {
    namespace = "dev.niek.flickrsearch.composeapp"

    buildFeatures {
        buildConfig = true
    }

    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "dev.niek.flickrsearch"
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isDefault = true
        }

        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}

buildkonfig {
    val keysPropertiesFile = rootProject.file("keys.properties")
    val keysProperties = Properties()
    if (keysPropertiesFile.exists()) {
        keysProperties.load(FileInputStream(keysPropertiesFile))
    }

    val flickrApiKey: String = keysProperties.getProperty("FLICKR_API_KEY", "")

    packageName = "dev.niek.flickrsearch"
    defaultConfigs {
        buildConfigField(BOOLEAN, "enableRemoteLogging", "true")
        buildConfigField(STRING, "flickrApiKey", flickrApiKey)
    }
}
