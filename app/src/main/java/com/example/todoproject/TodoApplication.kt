package com.example.todoproject

import com.example.todoproject.database.TodoDatabase

class TodoApplication : android.app.Application() {

    // Database instance
    val database by lazy { TodoDatabase.getDatabase(this) }

    // Repository instance
    val repository by lazy { TodoRepository(database.todoDao()) }
}