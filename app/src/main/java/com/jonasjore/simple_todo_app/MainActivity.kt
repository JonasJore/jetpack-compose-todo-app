package com.jonasjore.simple_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            TodoOverview(
                addNewTodoRoute =  { navController.navigate(Routes.addNewTodo) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.addNewTodo) {
            AddNewTodo(onBack = { navController.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTodoAppTheme {
        TodoOverview({ }, { })
    }
}