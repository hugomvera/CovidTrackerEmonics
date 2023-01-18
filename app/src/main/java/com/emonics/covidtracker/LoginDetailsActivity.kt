package com.emonics.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.emonics.covidtracker.databinding.ActivityLoginDetailsBinding
import com.google.firebase.auth.FirebaseAuth

class LoginDetailsActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginDetailsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener(){

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            // Checks for null user entries.
            if (email.isNotEmpty()  && password.isNotEmpty() ){

                    // Cross-checks w/ Firebase to see if user put in valid credentials.
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{

                        if(it.isSuccessful){
                            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, InfoChartPage::class.java )
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }

            }
            else{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

//     //Feature: Automatically sign-in the user based on credentials available in Firebase.
//     //If we want to implement this, we need to add a sign-out feature to the app first.
//    override fun onStart() {
//        super.onStart()
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this, InfoChartPage::class.java)
//            startActivity(intent)
//        }
//    }

}



// Older code to be used as a reference.
/*
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

 */