import com.morphine_coder.knowmerce.build_logic.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
class AndroidPresentationUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("multi.module.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }

}