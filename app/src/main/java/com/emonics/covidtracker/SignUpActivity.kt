package com.emonics.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val userName = findViewById<View>(R.id.username) as EditText
        val emailId = findViewById<View>(R.id.email) as EditText
        val password = findViewById<View>(R.id.password) as EditText
        val confirmPassword = findViewById<View>(R.id.repassword) as EditText

        val signupbtn = findViewById<Button>(R.id.signupbtn)
        signupbtn.setOnClickListener {
            println(userName)
        }



    }
}