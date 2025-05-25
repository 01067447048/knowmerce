plugins {
    alias(libs.plugins.multi.module.android.library.compose)
}

android {
    namespace = "com.morphine_coder.knowmerce.core.designsystem"

}

dependencies {

    androidTestImplementation(libs.androidx.espresso.core)
    implementation(projects.core.common)
    implementation(projects.core.model)
}