package com.jonasjore.simple_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jonasjore.simple_todo_app.navigation.Routes
import com.jonasjore.simple_todo_app.ui.enterAnimation
import com.jonasjore.simple_todo_app.ui.exitAnimation
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val todoOverviewViewModel: TodoOverviewViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTodoAppTheme {
                Scaffold {
                    NavigationComponent(todoOverviewViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun NavigationComponent(todoOverviewViewModel: TodoOverviewViewModel) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.todoOverView
    ) {
        composable(
            Routes.todoOverView
        ) {
            TodoOverviewScreen(
                todoOverviewViewModel = todoOverviewViewModel,
                addNewTodoRoute = { navController.navigate(Routes.addNewTodo) },
            ) { navController.popBackStack() }
        }
        composable(
            Routes.addNewTodo,
            enterTransition = {
                enterAnimation()
            },
            exitTransition = {
                exitAnimation()
            }
        ) {
            AddTodoScreen(addTodo = todoOverviewViewModel::addTodo) { navController.popBackStack() }
        }
    }
}
