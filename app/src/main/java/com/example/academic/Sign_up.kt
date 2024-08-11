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

class Sign_up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val firstname = findViewById<EditText>(R.id.firstname_input)
        val lastname = findViewById<EditText>(R.id.lastname_input)
        val username = findViewById<EditText>(R.id.Susername_input)
        val password = findViewById<EditText>(R.id.Spassword_input)
        val signupBtn = findViewById<Button>(R.id.SignUp_Btn)
        val textViewSignUp = findViewById<TextView>(R.id.textViewSignUp)

        signupBtn.setOnClickListener {
            val firstnameString = firstname.text.toString().trim()
            val lastnameString = lastname.text.toString().trim()
            val usernameString = username.text.toString().trim()
            val passwordString = password.text.toString().trim()

            if (firstnameString.isEmpty() || lastnameString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty()) {
                if (firstnameString.isEmpty()) firstname.error = "Enter your Firstname"
                if (lastnameString.isEmpty()) lastname.error = "Enter your Lastname"
                if (usernameString.isEmpty()) username.error = "Enter a username"
                if (passwordString.isEmpty()) password.error = "Enter a password"
                return@setOnClickListener
            }

            val registrationRequest = RegisterRequest(firstnameString, lastnameString, usernameString, passwordString)
            RetrofitInstance.api.registerUser(registrationRequest).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.success) {
                            val intent = Intent(this@Sign_up, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@Sign_up, "User Registered Successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@Sign_up, responseBody?.message ?: "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@Sign_up, "Registration failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@Sign_up, "Registration failed: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        textViewSignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
