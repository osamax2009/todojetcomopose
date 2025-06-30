package com.example.todoproject

import HomeScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
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

    // Get Repository from Application
    val application = context.applicationContext as TodoApplication
    val repository = application.repository

    // Create ViewModel with Factory
    val viewModelFactory = remember { TodoViewModelFactory(repository) }
    val todoViewModel: TodoViewModel = viewModel(factory = viewModelFactory)

    // Observe data from Room
    val allTodos by todoViewModel.allTodos.collectAsState(initial = emptyList())
    val totalCount by todoViewModel.totalCount.collectAsState(initial = 0)
    val completedCount by todoViewModel.completedCount.collectAsState(initial = 0)

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                totalTasks = totalCount,
                completedTasks = completedCount,
                onNavigateToTodos = {
                    navController.navigate(Screen.TodoList.route)
                },
                onClearCompleted = {  todoViewModel.clearCompleted() },
                onLogout = {
                    AuthPrefs.logout(context)
                    Log.d("HomeScreen", "Logout button clicked in nav")
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
                todoList = allTodos,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAddTodo ={ text ->
                    todoViewModel.addTodo(text)
                } ,
                onToggleTodo = { todo ->
                    todoViewModel.toggleTodo(todo)
                },
                onDeleteTodo = { todo ->
                    todoViewModel.deleteTodo(todo)
                }
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
