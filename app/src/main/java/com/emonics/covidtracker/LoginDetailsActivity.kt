package com.emonics.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_details)
        val username = findViewById<View>(R.id.username) as EditText
        val password = findViewById<View>(R.id.password) as EditText

        val submitClick = findViewById<Button>(R.id.login_button)
        submitClick.setOnClickListener {
            if(username.getText().toString().equals("user") && password.getText().toString().equals("password")){


            // println("You are logged in ")
                //TODO Navigate to Second screen
            }else{
                println("Sorry Try again")
                val intent = Intent(this, InfoChartPage::class.java)
                startActivity(intent)
            }
        }

    }
}