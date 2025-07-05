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
    val token : String?
)

data class ErrorResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: String?
)