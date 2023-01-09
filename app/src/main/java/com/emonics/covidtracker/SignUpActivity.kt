package com.emonics.covidtracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emonics.covidtracker.model.UserProfile
import com.google.firebase.database.*


class SignUpActivity : AppCompatActivity() {

    // creating a variable for our
    // Firebase Database.
    lateinit var firebaseDatabase: FirebaseDatabase


    // creating a variable for our Database
    // Reference for Firebase.
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val userName = findViewById<View>(R.id.username) as EditText
        val emailId = findViewById<View>(R.id.email) as EditText
        val password = findViewById<View>(R.id.password) as EditText
        val confirmPassword = findViewById<View>(R.id.repassword) as EditText

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("User")
         var userProfile =  UserProfile()

        val signUpBtn = findViewById<Button>(R.id.signupbtn)
        signUpBtn.setOnClickListener {

            userProfile.userName = userName.getText().toString()
            userProfile.emailId = emailId.getText().toString()
            userProfile.password = password.getText().toString()
            userProfile.confirmPassword = confirmPassword.getText().toString()
            addDatatoFirebase(userProfile)
        }
    }
    fun addDatatoFirebase(userProfile: UserProfile) {


        // we are use add value event listener method
        // which is called with database reference.
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference!!.setValue(userProfile)

                // after adding this data we are showing toast message.
                Toast.makeText(this@SignUpActivity, "data added", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(this@SignUpActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}