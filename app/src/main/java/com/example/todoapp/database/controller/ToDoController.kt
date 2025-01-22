package com.example.todoapp.database.controller

import android.content.Context
import com.example.todoapp.database.DBHelper
import com.example.todoapp.database.dataclass.ToDoDataClass

class ToDoController(context: Context) {
    private val dbHelper = DBHelper(context)

    fun addToDo(todo: ToDoDataClass): Long = dbHelper.addToDo(todo)

    fun getAllToDos(): List<ToDoDataClass> = dbHelper.getAllToDos()

    fun updateToDoStatus(id: Int, status: String): Int = dbHelper.updateToDoStatus(id, status)

    fun deleteToDo(id: Int): Int = dbHelper.deleteToDo(id)
}