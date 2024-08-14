package com.example.academic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.username_input)
        val password = findViewById<EditText>(R.id.password_input)
        val textViewSignUpNow = findViewById<TextView>(R.id.textViewSignUpNow)

        val loginButton = findViewById<Button>(R.id.loginbutton)
        loginButton.setOnClickListener {
            val usernameStr = username.text.toString().trim()
            val passwordStr = password.text.toString().trim()

            if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
                username.error = "Enter a username"
                password.error = "Enter a password"
            } else {
                val loginRequest = LoginRequest(usernameStr, passwordStr)
                RetrofitInstance.api.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.success == true) {
                                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                                startActivity(intent)
                                Toast.makeText(this@MainActivity, "Login successful", Toast.LENGTH_SHORT).show()
                            } else {
                                val message = loginResponse?.message ?: "Login failed: Username and Password are incorrect"
                                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "Login failed: Check Your Connection", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Connection Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        textViewSignUpNow.setOnClickListener {
            val intent = Intent(this, Sign_up::class.java)
            startActivity(intent)
        }
    }
}
