package com.example.todoproject

import com.example.todoproject.database.TodoDao
import com.example.todoproject.model.ToDoItem
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    fun getAllTodos(): Flow<List<ToDoItem>> = todoDao.getAllTodos()

    fun getActiveTodos(): Flow<List<ToDoItem>> = todoDao.getActiveTodos()

    fun getCompletedTodos(): Flow<List<ToDoItem>> = todoDao.getCompletedTodos()

    fun getTotalCount(): Flow<Int> = todoDao.getTotalCount()

    fun getCompletedCount(): Flow<Int> = todoDao.getCompletedCount()

    suspend fun insertTodo(text: String) {
        val todo = ToDoItem(text = text.trim())
        todoDao.insertTodo(todo)
    }

    suspend fun toggleTodo(todo: ToDoItem) {
        todoDao.updateTodo(todo.copy(isComplete = !todo.isComplete))
    }

    suspend fun deleteTodo(todo: ToDoItem) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteCompletedTodos() {
        todoDao.deleteCompletedTodos()
    }

    suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }
}
