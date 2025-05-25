plugins {
    alias(libs.plugins.multi.module.featrue)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.morphine_coder.knowmerce.feature.search"
}

dependencies {
    androidTestImplementation(libs.androidx.espresso.core)
}