plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1"
}

