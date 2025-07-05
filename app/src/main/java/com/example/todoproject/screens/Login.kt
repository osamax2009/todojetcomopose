package com.example.todoproject.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoproject.api.ApiClient
import com.example.todoproject.model.LoginRequest
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking


@Composable
fun Login(
    onLogin : (String) -> Unit
    ){
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage  by remember { mutableStateOf("") }




    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Welcome Back",
            fontSize = 28.sp ,
            fontWeight = FontWeight.Bold,
            )
        Spacer(modifier = Modifier.height(40.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phone,
            onValueChange = {
                 phone = it
                errorMessage = ""
            },
            label = {
                Text(text = "Username")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                errorMessage = ""
            },
            label = {
                Text(text = "password")
            },
            //hide password
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = null,
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (phone.isBlank() || password.isBlank()) {
                    errorMessage = "Username or password cannot be empty"
                    return@Button
                }

                coroutineScope.launch {
                    try {
                        val loginRequest = LoginRequest(phone, password)

                        val response = ApiClient.apiService.login(loginRequest)

                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            Log.d("LoginResponse", "Login response: $loginResponse")
                        }
                    } catch (e: Exception) {
                        Log.e("LoginError", "Login error: ${e.message}")
                    }
                }

                if (phone.length >= 3 && password.length >= 3) {


                } else {
                    errorMessage = "Username or password must be at least 3 characters"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ){
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            }else {
                Text(text = "Login" , fontSize = 16.sp)
            }
        }
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
    }
}