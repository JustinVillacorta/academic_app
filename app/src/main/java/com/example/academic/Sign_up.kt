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

class Sign_up : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        db = DatabaseHelper(this)
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

            if (firstnameString.isEmpty()){
                firstname.error = "Enter your Firstname"
                return@setOnClickListener
            }
            if (lastnameString.isEmpty()){
                lastname.error = "Enter your Lastname"
                return@setOnClickListener
            }

            if (usernameString.isEmpty()) {
                username.error = "Enter a username"
                return@setOnClickListener
            }

            if (passwordString.isEmpty()) {
                password.error = "Enter a password"
                return@setOnClickListener
            }

            if (db.isUsernameAvailable(usernameString)) {
                val isInserted = db.addUser(firstnameString, lastnameString, usernameString, passwordString, 0, 0, "")
                if (isInserted) {
                    Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
                    // Optionally, navigate to another activity or clear fields
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username is not available", Toast.LENGTH_SHORT).show()
            }
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
