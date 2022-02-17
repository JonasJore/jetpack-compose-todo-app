package com.jonasjore.simple_todo_app

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jonasjore.simple_todo_app.ui.theme.SimpleTodoAppTheme

@Composable
fun AddTodoScreen(onBack: () -> Unit) {
    var todoText by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(topBar = { TodoAppTopBar(onBack = onBack) }, content = {
        Surface {
            Column {
                H3(text = "New TODO")
                TextField(
                    value = todoText,
                    onValueChange = { todoText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text("New TODO") }
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(context, "something", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Cyan,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("SUBMIT TODO")
                    }
                }
            }
        }
    })
}

@Preview
@Composable
fun AddTodoPreview() {
    SimpleTodoAppTheme {
        AddTodoScreen { }
    }
}