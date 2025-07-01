package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object{
        const val KEY1  = "com.example.splashscreen.SignInActivity.mail"
        const val KEY2  = "com.example.splashscreen.SignInActivity.name"
        const val KEY3  = "com.example.splashscreen.SignInActivity.pass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.etName1)
        val password = findViewById<TextInputEditText>(R.id.etPass1)

        signInButton.setOnClickListener {
            // Take ref till "Users" node from Firebase Database!
            Log.d("DEBUG_SIGNIN", "Clicked Sign In button")
            val userNameString = userName.text.toString()
            val passInput = password.text.toString()
            if(userNameString.isNotEmpty() && passInput.isNotEmpty()){
                readData(userNameString, passInput)
            }
            else{
                Toast.makeText(this,"Please Enter Details",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        findViewById<TextView>(R.id.tvForgot).setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun readData(userNameString: String, passInput: String) {
        Log.d("DEBUG_SIGNIN", "Inside readData with: $userNameString")
        database = FirebaseDatabase.getInstance().getReference("Users")
        val safeKey = userNameString.replace(" ", "_")
        database.child(safeKey).get().addOnSuccessListener { //This time we added .get() to read data
            Log.d("DEBUG_SIGNIN", "Data snapshot: ${it.value}")
            //User Exist or Not?
            if(it.exists()) {
                //Welcome User
                val email = it.child("email").value as? String
                val name = it.child("name").value as? String
                val password = it.child("password").value as? String
                Log.d("DEBUG_SIGNIN", "Fetched: email=$email, name=$name, password=$password")
                // Null-check
                if (email == null || name == null || password == null) {
                    Toast.makeText(this, "Corrupted user data!", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }

                if (password == passInput) {
                    Log.d("SIGNIN_FLOW", "Password matched, launching ProfileActivity")
                    Intent(this, ProfileActivity::class.java).also { intent ->
                        intent.putExtra(KEY1, email)
                        intent.putExtra(KEY2, name)
                        intent.putExtra(KEY3, password)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Wrong password!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"User doesn't exist Sign Up first!",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{//Failure of Operation
            Log.e("DEBUG_SIGNIN", "Firebase fetch failed", it)
            Toast.makeText(this,"Error202 : Error in Fetching",Toast.LENGTH_SHORT).show()
        }
    }
}