package com.jonasjore.simple_todo_app

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jonasjore.simple_todo_app.data.toEntity
import com.jonasjore.simple_todo_app.ui.composables.cards.TodoCard
import com.jonasjore.simple_todo_app.ui.composables.headers.H3
import com.jonasjore.simple_todo_app.ui.composables.topbar.TodoAppTopBar

@ExperimentalMaterialApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoOverviewScreen(
    todoOverviewViewModel: TodoOverviewViewModel,
    addNewTodoRoute: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TodoAppTopBar(onBack = onBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = addNewTodoRoute) {
                Icon(Icons.Default.Add, stringResource(R.string.add_todo_icon_icon_description))
            }
        }) {
        val todos by remember { mutableStateOf(todoOverviewViewModel.todosState) }
        Surface {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    H3(text = stringResource(id = R.string.todo_overview_header))
                }
                items(todos, key = { it.id }) { todo ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            if (dismissValue == DismissValue.DismissedToStart) {
                                todos.remove(todo)
                                todoOverviewViewModel.removeTodo(todo)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(.2f) },
                        background = {
                            val dismissDirection =
                                dismissState.dismissDirection ?: return@SwipeToDismiss
                            val dismissBoxColor by animateColorAsState(
                                targetValue = Color.Red
                            )
                            val dismissIcon = when (dismissDirection) {
                                DismissDirection.StartToEnd -> Icons.Default.Done
                                DismissDirection.EndToStart -> Icons.Default.Delete
                            }
                            val dismissIconScale by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.Default)
                                    0.8f
                                else 1.2f
                            )
                            val dismissContentAlignment = when (dismissDirection) {
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                            }
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .fillMaxSize()
                                    .background(dismissBoxColor),
                                contentAlignment = dismissContentAlignment
                            ) {
                                Icon(
                                    imageVector = dismissIcon,
                                    contentDescription = stringResource(id = R.string.swipe_to_dismiss_action_icon_description),
                                    modifier = Modifier
                                        .scale(dismissIconScale)
                                        .padding(end = 8.dp)
                                )
                            }
                        },
                        dismissContent = {
                            TodoCard(
                                todoTask = todo.toEntity(),
                                updateTodo = todoOverviewViewModel::updateTodo,
                                dismissState = dismissState
                            )
                        }
                    )
                }
            }
        }

    }
}
