package com.example.splashscreen

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private fun validateInputs(name: String, email: String, pass: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isStrongPassword(pass)) {
            Toast.makeText(this,
                "Password must be 8+ chars, include uppercase, digit & special",
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<EditText>(R.id.etName)
        val etMail = findViewById<EditText>(R.id.etMail)
        val etPass = findViewById<EditText>(R.id.etPass)

        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val pass = etPass.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).get().addOnSuccessListener {
                if(it.exists()){
                    Toast.makeText(this,"User Already Exist - please Sign In!",Toast.LENGTH_SHORT).show()
                }
                else{
                    val user = User(name, mail, pass)
                    database.child(name).setValue(user).addOnSuccessListener {
                        etName.text.clear()
                        etMail.text.clear()
                        etPass.text.clear()
                        Toast.makeText(this,"User Registered ✅",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener{
                        Toast.makeText(this,"Failed ❌",Toast.LENGTH_SHORT).show()
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this,
                    "Network error – try again.",
                    Toast.LENGTH_SHORT).show()
            }
        }

        val signInText = findViewById<TextView>(R.id.tvSignIn)
        signInText.setOnClickListener {
            val intent = android.content.Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
    private fun isStrongPassword(pw: String): Boolean {
        // at least 8 chars, one upper, one digit, one special
        val pattern = Regex("""^(?=.*[A-Z])(?=.*\d)(?=.*[@#\$%^&+=!]).{8,}$""")
        return pattern.matches(pw)
    }
}