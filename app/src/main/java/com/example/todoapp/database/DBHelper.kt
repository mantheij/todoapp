package com.example.todoapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.database.dataclass.ToDoDataClass

class DBHelper(context: Context) : SQLiteOpenHelper(context, "ToDo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE ToDo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                prioritaet TEXT,
                endzeitpunkt TEXT,
                beschreibung TEXT,
                status TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ToDo")
        onCreate(db)
    }

    fun addToDo(todo: ToDoDataClass): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", todo.name)
            put("prioritaet", todo.prioritaet)
            put("endzeitpunkt", todo.endzeitpunkt)
            put("beschreibung", todo.beschreibung)
            put("status", todo.status)
        }
        return db.insert("ToDo", null, values)
    }

    fun getAllToDos(): List<ToDoDataClass> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ToDo", null)
        val todos = mutableListOf<ToDoDataClass>()

        if (cursor.moveToFirst()) {
            do {
                val todo = ToDoDataClass(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    prioritaet = cursor.getString(cursor.getColumnIndexOrThrow("prioritaet")),
                    endzeitpunkt = cursor.getString(cursor.getColumnIndexOrThrow("endzeitpunkt")),
                    beschreibung = cursor.getString(cursor.getColumnIndexOrThrow("beschreibung")),
                    status = cursor.getString(cursor.getColumnIndexOrThrow("status"))
                )
                todos.add(todo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return todos
    }

    fun updateToDoStatus(id: Int, status: String): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("status", status)
        }
        return db.update("ToDo", values, "id=?", arrayOf(id.toString()))
    }

    fun deleteToDo(id: Int): Int {
        val db = this.writableDatabase
        return db.delete("ToDo", "id=?", arrayOf(id.toString()))
    }
}