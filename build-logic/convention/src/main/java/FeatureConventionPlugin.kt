import com.morphine_coder.knowmerce.build_logic.convention.addFeatureDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
class FeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("multi.module.android.presentation.ui")
                apply("androidx.navigation.safeargs.kotlin")
                apply("multi.module.android.hilt")
            }

            dependencies {
                addFeatureDependencies(this@run)
            }
        }
    }

}