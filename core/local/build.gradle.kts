plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.morphine_coder.knowmerce.core.local"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
    }
}

dependencies {


    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    implementation(projects.core.domain)
    implementation(projects.core.common)
    implementation(projects.core.model)
}