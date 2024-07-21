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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)


        val username = findViewById<EditText>(R.id.Susername_input)
        val password = findViewById<EditText>(R.id.Spassword_input)
        val SignupBtn = findViewById<Button>(R.id.SignUp_Btn)
        val textViewSignUp = findViewById<TextView>(R.id.textViewSignUp)



        SignupBtn.setOnClickListener {

            val usernameString = username.text.toString().trim()
            val passwordString = password.text.toString().trim()


            if ( usernameString.isEmpty() || passwordString.isEmpty())
                username.error = "Enter a username"
                password.error = "Enter a password"
        }










        val content = SpannableString("Log in")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        textViewSignUp.text = content

        textViewSignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        }
    }
