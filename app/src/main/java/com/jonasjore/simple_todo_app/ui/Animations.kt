package com.jonasjore.simple_todo_app.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.exitAnimation(): ExitTransition {
    return slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Right,
        animationSpec = tween()
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun AnimatedContentScope<NavBackStackEntry>.enterAnimation(): EnterTransition {
    return slideIntoContainer(
        AnimatedContentScope.SlideDirection.Left,
        animationSpec = tween()
    )
}