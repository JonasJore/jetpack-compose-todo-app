package com.jonasjore.simple_todo_app.dependencies

private object Versions {
    const val composeVersion = "1.1.0"
    const val roomVersion = "2.4.1"
    const val hiltVersion = "2.38.1"
    const val navigationComposeVersion = "2.5.0-alpha01"
}

object Dependencies {
    object Androidx {
        val coreKtx by lazy { "androidx.core:core-ktx:1.7.0" }
        val lifeCycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1" }
        val activityCompose by lazy { "androidx.activity:activity-compose:1.3.1" }
    }

    object Compose {
        val composeUI by lazy { "androidx.compose.ui:ui:${Versions.composeVersion}" }
        val material by lazy { "androidx.compose.material:material:${Versions.composeVersion}" }
        val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}" }
        val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.composeVersion}" }
        val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}" }
    }

    object Kotlinx {
        val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9" }
    }

    object Room {
        val runtime by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
        val annotationCompiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
        val roomKotlin by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }
        val roomKapt by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
    }

    object Hilt {
        val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
        val hiltKapt by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }
    }
}