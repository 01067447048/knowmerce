plugins {
    alias(libs.plugins.multi.module.android.application.compose)
    alias(libs.plugins.multi.module.android.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.morphine_coder.knowmerce"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.local)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.data)

    implementation(projects.feature.savedList)
    implementation(projects.feature.search)

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}