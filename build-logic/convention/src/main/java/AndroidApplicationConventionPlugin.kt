import com.android.build.api.dsl.ApplicationExtension
import com.morphine_coder.knowmerce.build_logic.convention.ExtensionTypes
import com.morphine_coder.knowmerce.build_logic.convention.configureBuildTypes
import com.morphine_coder.knowmerce.build_logic.convention.configureKotlinAndroid
import com.morphine_coder.knowmerce.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionTypes = ExtensionTypes.APPLICATION
                )

                defaultConfig {
                    applicationId = libs.findVersion("applicationId").get().toString()
                    compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
                    targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
                    minSdk = libs.findVersion("minSdk").get().toString().toInt()
                    versionCode = libs.findVersion("versionCode").get().toString().toInt()
                    versionName = libs.findVersion("versionName").get().toString()
                }

                dependencies {
                    add("implementation", libs.findBundle("paging").get())
                }
            }
        }
    }

}