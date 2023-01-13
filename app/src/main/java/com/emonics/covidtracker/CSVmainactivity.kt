package com.emonics.covidtracker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CSVmainactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csvmainactivity)


        val textView: TextView = findViewById(R.id.txtVCSV) as TextView

        val extras = intent.extras
        if (extras != null) {
            val value = extras.getString("arg1")
            textView.setText(value)
            //The key argument here must match that used in the other activity
        }
    }
}