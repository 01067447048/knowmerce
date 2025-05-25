import com.android.build.api.dsl.LibraryExtension
import com.morphine_coder.knowmerce.build_logic.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("multi.module.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.getByType<LibraryExtension>().run {
                configureAndroidCompose(this)
            }
        }
    }
}