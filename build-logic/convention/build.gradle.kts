import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.morphine_coder.knowmerce.buildlogic"

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "multi.module.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "multi.module.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "multi.module.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "multi.module.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidPresentationUi") {
            id = "multi.module.android.presentation.ui"
            implementationClass = "AndroidPresentationUiConventionPlugin"
        }

        register("androidHilt") {
            id = "multi.module.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("javaLibrary") {
            id = "multi.module.java.library"
            implementationClass = "JavaLibraryConventionPlugin"
        }

        register("feature") {
            id = "multi.module.feature"
            implementationClass = "FeatureConventionPlugin"
        }
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}