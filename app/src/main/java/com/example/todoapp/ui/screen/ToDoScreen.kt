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
fun ToDoScreen(controller: ToDoController) {
    val todos by remember { mutableStateOf(controller.getAllToDos().filter { it.status == "offen" }) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Aktive ToDos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Add Navigation for Adding ToDo */ }) {
                Text("+")
            }
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            items(todos.size) { index ->
                ToDoCard(todo = todos[index], onStatusChange = {
                    controller.updateToDoStatus(todos[index].id, "erledigt")
                }, onDelete = {
                    controller.deleteToDo(todos[index].id)
                })
            }
        }
    }
}

@Composable
fun ToDoCard(todo: ToDoDataClass, onStatusChange: () -> Unit, onDelete: () -> Unit) {
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
            Row {
                Button(onClick = onStatusChange) { Text("Erledigt") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(MaterialTheme.colors.error)) {
                    Text("Löschen")
                }
            }
        }
    }
}