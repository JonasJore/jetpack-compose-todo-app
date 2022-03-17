package com.jonasjore.simple_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    private val todoOverviewViewModel: TodoOverviewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimpleTodoAppTheme {
                Scaffold {
                    NavigationComponent(navController, todoOverviewViewModel)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    todoOverviewViewModel: TodoOverviewViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.todoOverView
    ) {
        composable(Routes.todoOverView) {
            TodoOverviewScreen(
                todoOverviewViewModel = todoOverviewViewModel,
                addNewTodoRoute = { navController.navigate(Routes.addNewTodo) },
            ) { navController.popBackStack() }
        }
        composable(Routes.addNewTodo) {
            AddTodoScreen(addTodo = todoOverviewViewModel::addTodo) { navController.popBackStack() }
        }
    }
}
