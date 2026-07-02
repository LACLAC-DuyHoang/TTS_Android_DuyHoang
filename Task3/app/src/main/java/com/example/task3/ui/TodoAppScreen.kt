package com.example.task3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Todo(
    val id: Int,
    val title: String
)
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppScreen() {
    var todoText by remember { mutableStateOf("") }
    var nextId by remember { mutableIntStateOf(4) }

    val todos = remember {
        mutableStateListOf(
            Todo(1, "Học Kotlin"),
            Todo(2, "Học Jetpack Compose"),
            Todo(3, "Làm Todo App")
        )
    }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách công việc") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TodoInput(
                text = todoText,
                onTextChange = { todoText = it },
                onAddClick = {
                    if (todoText.isNotBlank()) {
                        todos.add(
                            Todo(
                                id = nextId,
                                title = todoText.trim()
                            )
                        )
                        nextId++
                        todoText = ""
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = todos.isNotEmpty()
            ) {
                Text("Cuộn lên đầu")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (todos.isEmpty()) {
                Text(
                    text = "Chưa có công việc nào",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    state = listState,
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = todos,
                        key = { todo -> todo.id }
                    ) { todo ->
                        TodoItem(
                            todo = todo,
                            onDelete = { todoId ->
                                todos.removeAll { it.id == todoId }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onAddClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Nhập công việc") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Button(
            onClick = onAddClick,
            enabled = text.isNotBlank()
        ) {
            Text("Thêm")
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )

            TextButton(
                onClick = { onDelete(todo.id) }
            ) {
                Text("Xóa")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    MaterialTheme {
        TodoAppScreen()
    }
}