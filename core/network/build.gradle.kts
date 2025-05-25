plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.morphine_coder.knowmerce.core.network"
}

dependencies {

    implementation(libs.bundles.retrofit)
    implementation(projects.core.common)
}