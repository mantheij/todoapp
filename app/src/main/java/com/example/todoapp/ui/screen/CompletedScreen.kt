package com.example.todoapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.database.controller.ToDoController
import com.example.todoapp.database.dataclass.ToDoDataClass

@Composable
fun CompletedScreen(controller: ToDoController) {
    val todos by remember { mutableStateOf(controller.getAllToDos().filter { it.status == "erledigt" }) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Erledigte ToDos") })
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            items(todos.size) { index ->
                CompletedToDoCard(todo = todos[index])
            }
        }
    }
}

@Composable
fun CompletedToDoCard(todo: ToDoDataClass) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todo.name, style = MaterialTheme.typography.h6)
            Text(text = "Priorität: ${todo.prioritaet}")
            Text(text = "Endzeit: ${todo.endzeitpunkt}")
            Text(text = todo.beschreibung)
            Text(text = "Status: Erledigt", color = MaterialTheme.colors.primary)
        }
    }
}