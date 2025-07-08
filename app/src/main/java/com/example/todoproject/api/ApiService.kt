package com.example.todoproject.api

import com.example.todoproject.model.LoginRequest
import com.example.todoproject.model.LoginResponse
import com.example.todoproject.model.TodosResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("todos")
    suspend fun getTodos(): Response<TodosResponse>


}