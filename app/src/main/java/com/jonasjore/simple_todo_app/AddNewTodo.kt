package com.jonasjore.simple_todo_app

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AddNewTodo(onBack: () -> Unit) {
    Scaffold(topBar = { TodoAppTopBar(onBack = onBack) }, content = {
        Surface {
            Column {
                Text(text = "add new todo")
            }
        }
    })
}