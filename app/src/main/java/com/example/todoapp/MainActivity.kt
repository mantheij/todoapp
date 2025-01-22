package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.database.controller.ToDoController
import com.example.todoapp.ui.screen.CompletedScreen
import com.example.todoapp.ui.screen.ToDoScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val controller = ToDoController(this)

        setContent {
            ToDoApp(controller)
        }
    }
}

@Composable
fun ToDoApp(controller: ToDoController) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "todo_screen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("todo_screen") { ToDoScreen(controller) }
            composable("completed_screen") { CompletedScreen(controller) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Aktive ToDos") },
            label = { Text("Aktive ToDos") },
            selected = false, // Hier könntest du die aktuelle Route prüfen
            onClick = { navController.navigate("todo_screen") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Done, contentDescription = "Erledigte ToDos") },
            label = { Text("Erledigte ToDos") },
            selected = false, // Hier könntest du die aktuelle Route prüfen
            onClick = { navController.navigate("completed_screen") }
        )
    }
}