package com.emonics.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.loginButon)

        loginButton.setOnClickListener {
            val intent = Intent(this, InfoChartPage::class.java)
            // start your next activity
            startActivity(intent)
        }



    }
}