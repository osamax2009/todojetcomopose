package com.example.todoproject.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val status:  Boolean,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: UserData?
)


data class UserData(
    @SerializedName("token")
    val token: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("user_id")
    val userId: Int?
)

data class ErrorResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: String?
)