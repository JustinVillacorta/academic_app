package com.example.academic

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        val username = findViewById<EditText>(R.id.username_input)
        val password = findViewById<EditText>(R.id.password_input)
        val textViewSignUpNow = findViewById<TextView>(R.id.textViewSignUpNow)

        val content = SpannableString("Sign up now")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        textViewSignUpNow.text = content

        textViewSignUpNow.setOnClickListener {
            val intent = Intent(this, Sign_up::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById<Button>(R.id.loginbutton)
        loginButton.setOnClickListener {
            val usernameStr = username.text.toString().trim()
            val passwordStr = password.text.toString().trim()

            if (usernameStr.isEmpty() || passwordStr.isEmpty()) {
                username.error = "Enter a username"
                password.error = "Enter a password"
            } else {
                if (db.isUserExist(usernameStr)) {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Please wait for approval of your enrollment", Toast.LENGTH_SHORT).show()
                } else {
                    username.error = "Invalid username or password"
                    password.error = "Invalid username or password"
                }
            }
        }
    }
}
