package com.example.todoproject.model

import com.google.gson.annotations.SerializedName

data class TodosResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: List<BackendTodoItem>
)
data class BackendTodoItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("isComplete")
    val isComplete: Boolean,
    @SerializedName("createdAt")
    val createdAt: Long
)