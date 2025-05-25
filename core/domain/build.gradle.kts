plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.morphine_coder.knowmerce.core.domain"
}

dependencies {

    androidTestImplementation(libs.androidx.espresso.core)
    implementation(projects.core.common)
    implementation(projects.core.model)
}