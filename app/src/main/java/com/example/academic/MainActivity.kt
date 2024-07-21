package com.example.academic

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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
                val parser = resources.getXml(R.xml.account)
                var foundCredentials = false

                while (parser.eventType != XmlPullParser.END_DOCUMENT) {
                    if (parser.eventType == XmlPullParser.START_TAG && parser.name == "account") {
                        val storedUsername = parser.getAttributeValue(null, "username")
                        val storedPassword = parser.getAttributeValue(null, "password")

                        Log.d("MainActivity", "Stored: $storedUsername - $storedPassword")
                        if (storedUsername == usernameStr && storedPassword == passwordStr) {
                            val intent = Intent(this, MainActivity2::class.java)
                            startActivity(intent)
                            foundCredentials = true
                            break
                        }
                    }
                    parser.next()
                }

                if (!foundCredentials) {
                    username.error = "Invalid username or password"
                    password.error = "Invalid username or password"
                }
            }
        }
    }
}








