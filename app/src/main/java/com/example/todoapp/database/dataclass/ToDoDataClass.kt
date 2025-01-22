package com.example.todoapp.database.dataclass

data class ToDoDataClass(
    val id: Int,
    val name: String,
    val prioritaet: String,
    val endzeitpunkt: String,
    val beschreibung: String,
    val status: String // "offen" oder "erledigt"
)