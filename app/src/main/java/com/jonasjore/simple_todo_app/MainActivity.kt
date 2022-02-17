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
    val repository = TodoRepository(applicationContext = applicationContext)
    NavHost(
        navController = navController,
        startDestination = Routes.todoOverView
    ) {
        composable(Routes.todoOverView) {
            TodoOverviewScreen(
                getTodos = repository::getAllTodos,
                updateTodo = repository::updateTodo,
                addNewTodoRoute = { navController.navigate(Routes.addNewTodo) },
            ) { navController.popBackStack() }
        }
        composable(Routes.addNewTodo) {
            AddTodoScreen(addTodo = repository::addTodo) { navController.popBackStack() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    fun todoList(): List<TodoTask> = listOf(
        TodoTask(isDone = false, task = "Gjøre lekse"),
        TodoTask(isDone = true, task = "Runne resident evil village"),
        TodoTask(isDone = false, task = "Jobbe videre med todo app"),
        TodoTask(isDone = true, task = "Støvsuge"),
        TodoTask(isDone = false, task = "Bære ved"),
        TodoTask(isDone = true, task = "Vaske badet"),
        TodoTask(isDone = true, task = "Oppvask kjøkken"),
        TodoTask(isDone = true, task = "Lage kaffe"),
    )
    SimpleTodoAppTheme {
        TodoOverviewScreen(
            addNewTodoRoute = { },
            onBack = { },
            getTodos = { todoList() },
            updateTodo = { l: Long, b: Boolean -> })
    }
}