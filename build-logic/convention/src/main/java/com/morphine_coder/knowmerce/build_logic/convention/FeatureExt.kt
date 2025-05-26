package com.morphine_coder.knowmerce.build_logic.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 24.
 */
fun DependencyHandlerScope.addFeatureDependencies(project: Project) {
    add("implementation", project.libs.findBundle("compose").get())
    add("implementation", project.libs.findLibrary("androidx.appcompat").get())
    add("implementation", project.libs.findLibrary("androidx.core.ktx").get())
    add("implementation", project.libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
    add("implementation", project.libs.findBundle("navigation").get())

    add("implementation", project(":core:common"))
    add("implementation", project(":core:domain"))
    add("implementation", project(":core:model"))
    add("implementation", project(":core:designsystem"))
}