package com.example.splashscreen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etUser = findViewById<EditText>(R.id.etName2)
        val etNewPw = findViewById<EditText>(R.id.etPass2)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val username = etUser.text.toString().trim()
            val newPw = etNewPw.text.toString()
            if (username.isEmpty() || newPw.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isStrongPassword(newPw)) {
                Toast.makeText(this,
                    "Weak password – must be 8+ chars, include uppercase, digit & special",
                    Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            dbRef = FirebaseDatabase.getInstance().getReference("Users")
            dbRef.child(username).get().addOnSuccessListener { snap ->
                if (snap.exists()) {
                    // update only the password field
                    dbRef.child(username)
                        .child("password")
                        .setValue(newPw)
                        .addOnSuccessListener {
                            Toast.makeText(this,
                                "Password updated! Please Sign In.",
                                Toast.LENGTH_LONG).show()
                            finish()  // back to SignIn
                        }.addOnFailureListener {
                            Toast.makeText(this,
                                "Update failed – try again.",
                                Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this,
                        "User not found – please Sign Up first.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun isStrongPassword(pw: String): Boolean {
        val pattern = Regex("""^(?=.*[A-Z])(?=.*\d)(?=.*[@#\$%^&+=!]).{8,}$""")
        return pattern.matches(pw)
    }
}