package com.jonasjore.simple_todo_app

import android.content.Context
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
import androidx.room.Room
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SimpleTodoAppTheme {
                Scaffold {
                    NavigationComponent(navController, applicationContext)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, applicationContext: Context) {
    val db: TodoDatabase = Room.databaseBuilder(
        applicationContext,
        TodoDatabase::class.java, "todo_database"
    ).build()
    val todoTaskDao = db.TodoTaskDao()
    NavHost(
        navController = navController,
        startDestination = Routes.todoOverView
    ) {
        composable(Routes.todoOverView) {
            TodoOverviewScreen(
                todoTaskDao = todoTaskDao,
                addNewTodoRoute = { navController.navigate(Routes.addNewTodo) },
            ) { navController.popBackStack() }
        }
        composable(Routes.addNewTodo) {
            AddTodoScreen(todoTaskDao = todoTaskDao) { navController.popBackStack() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleTodoAppTheme {
        TodoOverviewScreen(addNewTodoRoute = { }, onBack = { })
    }
}