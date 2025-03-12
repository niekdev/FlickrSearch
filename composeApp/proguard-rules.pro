# Keep Koin modules
-keep class dev.niek.flickrsearch.data.datasources.database.di.** { *; }
-keep class dev.niek.flickrsearch.data.datasources.network.di.** { *; }
-keep class dev.niek.flickrsearch.data.di.** { *; }
-keep class dev.niek.flickrsearch.domain.di.** { *; }
-keep class dev.niek.flickrsearch.presentation.di.** { *; }

# Keep Compose navigation
-keep class dev.niek.flickrsearch.presentation.navigation.** { *; }

# Keep Kotlin companion objects
-keepclassmembers class **$Companion {
    *;
}

# Keep Kotlin module files (Kt files)
-keep class **.di.*ModuleKt { *; }
