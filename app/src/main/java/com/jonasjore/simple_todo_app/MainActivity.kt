package com.jonasjore.simple_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimpleTodoAppTheme {
                Scaffold {
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.todoOverView
    ) {
        composable(Routes.todoOverView) {
            TodoOverviewScreen(
                addNewTodoRoute =  { navController.navigate(Routes.addNewTodo) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.addNewTodo) {
            AddTodoScreen(onBack = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTodoAppTheme {
        TodoOverviewScreen({ }, { })
    }
}