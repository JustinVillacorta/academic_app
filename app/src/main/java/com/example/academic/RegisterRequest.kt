package com.example.academic

// RegisterRequest.kt
data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val password: String
)