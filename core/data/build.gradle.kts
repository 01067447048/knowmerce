plugins {
    alias(libs.plugins.multi.module.android.library)
    alias(libs.plugins.multi.module.android.hilt)
}

android {
    namespace = "com.monphine_coder.knowmerce.core.data"
}

dependencies {
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.local)
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.paging)
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
}