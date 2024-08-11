package com.example.academic

// ApiService.kt
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("login.php")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("register.php")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}
