package com.jonasjore.simple_todo_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme
import java.util.UUID

@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
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
                deleteTodo = repository::removeTodo,
                addNewTodoRoute = { navController.navigate(Routes.addNewTodo) },
            ) { navController.popBackStack() }
        }
        composable(Routes.addNewTodo) {
            AddTodoScreen(addTodo = repository::addTodo) { navController.popBackStack() }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    fun r() =
        (0..1000).random()

    fun todoList(): List<TodoTask> = listOf(
        TodoTask(
            isDone = false,
            task = "Gjøre lekser",
            id = r()
        ),
        TodoTask
            (
            isDone = true,
            task = "Runne resident evil village",
            id = r()
        ),
        TodoTask(
            isDone = false,
            task = "Jobbe videre med todo app",
            id = r()
        ),
        TodoTask(
            isDone = true,
            task = "Støvsuge",
            id = r()
        ),
        TodoTask(
            isDone = false,
            task = "Bære ved",
            id = r()
        ),
        TodoTask(
            isDone = true,
            task = "Vaske badet",
            id = r()
        ),
        TodoTask(
            isDone = true,
            task = "Oppvask kjøkken",
            id = r()
        ),
        TodoTask(
            isDone = true,
            task = "Lage kaffe",
            id = r()
        ),
    )
    SimpleTodoAppTheme {
        TodoOverviewScreen(
            addNewTodoRoute = { },
            onBack = { },
            getTodos = { todoList() },
            updateTodo = { l: Int, b: Boolean -> },
            deleteTodo = { d -> }
        )
    }
}