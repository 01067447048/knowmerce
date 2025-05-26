package com.morphine_coder.knowmerce.build_logic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionTypes: ExtensionTypes,
) {
    commonExtension.run {

        buildFeatures {
            buildConfig = true
            flavorDimensions += "contentType"
        }

        when (extensionTypes) {
            ExtensionTypes.APPLICATION -> {
                extensions.configure<ApplicationExtension>() {
                    buildTypes {
                        debug {
                            configureDebugBuildType()
                            applicationIdSuffix = ".dev"
                        }
                        release {
                            configureReleaseBuildType(commonExtension)
                        }
                    }
                }
            }

            ExtensionTypes.LIBRARY -> {
                extensions.configure<LibraryExtension>() {
                    buildTypes {
                        debug {
                            configureDebugBuildType()
                        }
                        release {
                            configureReleaseBuildType(commonExtension)
                        }
                    }
                }
            }
        }

        productFlavors {

        }
    }

}

private fun BuildType.configureDebugBuildType() {
    buildConfigField("String", "KAKAO_KEY", "\"7b0d4c2078f68def627090db21dc7daa\"")
    buildConfigField("String", "BASE_URL", "\"https://dapi.kakao.com/\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    buildConfigField("String", "KAKAO_KEY", "\"7b0d4c2078f68def627090db21dc7daa\"")
    buildConfigField("String", "BASE_URL", "\"https://dapi.kakao.com/\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}