package com.emonics.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginClick = findViewById<Button>(R.id.login_button)
        loginClick.setOnClickListener {
            val intent = Intent(this, LoginDetailsActivity::class.java)
             startActivity(intent)
        }

        val signupClick = findViewById<Button>(R.id.signup_button)
        signupClick.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}