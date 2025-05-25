import com.android.build.api.dsl.LibraryExtension
import com.morphine_coder.knowmerce.build_logic.convention.ExtensionTypes
import com.morphine_coder.knowmerce.build_logic.convention.configureAndroidCompose
import com.morphine_coder.knowmerce.build_logic.convention.configureBuildTypes
import com.morphine_coder.knowmerce.build_logic.convention.configureKotlinAndroid
import com.morphine_coder.knowmerce.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionTypes = ExtensionTypes.LIBRARY
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                dependencies {
                    add("implementation", libs.findBundle("paging").get())
                }
            }
        }
    }

}