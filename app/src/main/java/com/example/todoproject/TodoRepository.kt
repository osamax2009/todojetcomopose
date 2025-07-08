package com.example.todoproject

import android.util.Log
import com.example.todoproject.api.ApiClient
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
    suspend fun syncTodosFromBackend(): Boolean {
        return try {
            Log.d("TodoSync", "🔄 Syncing todos from backend...")

            val response = ApiClient.apiService.getTodos()

            if (response.isSuccessful) {
                val todosResponse = response.body()

                if (todosResponse?.status == true && todosResponse.data.isNotEmpty()) {
                    // Clear existing todos (optional)
                    todoDao.deleteAllTodos()

                    // Convert backend todos to local todos and save
                    todosResponse.data.forEach { backendTodo ->
                        val localTodo = ToDoItem(
                            id = 0, // Auto-generate new ID for local database
                            text = backendTodo.text,
                            isComplete = backendTodo.isComplete,
                            createdAt = backendTodo.createdAt
                        )
                        todoDao.insertTodo(localTodo)
                    }

                    Log.d("TodoSync", "✅ Synced ${todosResponse.data.size} todos successfully")
                    true
                } else {
                    Log.w("TodoSync", "⚠️ No todos received from backend")
                    false
                }
            } else {
                Log.e("TodoSync", "❌ Backend sync failed: ${response.code()}")
                false
            }

        } catch (e: Exception) {
            Log.e("TodoSync", "❌ Sync error: ${e.message}", e)
            false
        }
    }
}
