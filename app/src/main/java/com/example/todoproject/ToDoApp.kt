package com.example.todoproject

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoproject.model.Screen
import com.example.todoproject.model.ToDoItem
import com.example.todoproject.screens.Login
import com.example.todoproject.screens.ObBoardingScreen
import com.example.todoproject.screens.SplashScreen
import com.example.todoproject.screens.TodoScreen


@Composable
fun ToDoApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    var todoList: List<ToDoItem> by remember { mutableStateOf(emptyList()) }
    var nextId: Int by remember { mutableStateOf(1) }

    // Helper functions
    val addTodo = { text: String ->
        if (text.isNotBlank()) {
            todoList = todoList + ToDoItem(id = nextId, text = text.trim())
            nextId++
        }
    }

    val toggleTodo = { id: Int ->
        todoList = todoList.map { todo ->
            if (todo.id == id) todo.copy(isComplete = !todo.isComplete)
            else todo
        }
    }

    val deleteTodo = { id: Int ->
        todoList = todoList.filter { it.id != id }
    }

    val clearCompleted = {
        todoList = todoList.filter { !it.isComplete }
    }

    // Calculate stats
    val totalTasks = todoList.size
    val completedTasks = todoList.count { it.isComplete }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                totalTasks = totalTasks,
                completedTasks = completedTasks,
                onNavigateToTodos = {
                    navController.navigate(Screen.TodoList.route)
                },
                onClearCompleted = clearCompleted,
                onLogout = {
                    AuthPrefs.logout(context)
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
            })
        }

        // Todo List Screen
        composable(Screen.TodoList.route) {
            TodoScreen(
                todoList = todoList,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAddTodo = addTodo,
                onToggleTodo = toggleTodo,
                onDeleteTodo = deleteTodo
            )
        }
        composable(Screen.OnBoarding.route) {

            ObBoardingScreen(
                onComplete = {
                    AuthPrefs.setShowOnboardingStatus(context)
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.Splash.route) {
            SplashScreen { route ->
                navController.navigate(route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.Login.route) {
            Login(
                onLogin = { username ->
                    AuthPrefs.saveLogin(context, username)
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
