package com.example.academic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val username = findViewById<EditText>(R.id.username_input)
        val password = findViewById<EditText>(R.id.password_input)

        val secondActbutton = findViewById<Button>(R.id.loginbutton)
        secondActbutton.setOnClickListener {
            // Check if the username is emptys
            if (username.text.toString().trim().isEmpty() && password.text.toString().trim().isEmpty()) {
                username.error = "Enter a username" // Set username error message
                password.error = "Enter a password" // Set password error message
            } else if (username.text.toString().trim().isEmpty()) {
                username.error = "Enter a username" // Set username error message
            } else if (password.text.toString().trim().isEmpty()) {
                password.error = "Enter a password" // Set password error message
            } else {
                // Both username and password are not empty, proceed with the intent
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}



