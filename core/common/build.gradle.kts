plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.morphine_coder.knowmerce.core.common"

}

dependencies {

    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.bundles.navigation)
    implementation(projects.core.model)
}