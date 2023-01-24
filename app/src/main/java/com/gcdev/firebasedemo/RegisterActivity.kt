package com.gcdev.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val register_to_login: TextView = findViewById(R.id.textView_Register_to_Login)

        // already have an account text, and can log in
        register_to_login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btnregister: Button = findViewById(R.id.buttonRegister)

        btnregister.setOnClickListener{
            performSignUp()
        }

    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.editTextEmail_Register)
        val password = findViewById<EditText>(R.id.editPassword_Register)

        //verify that form is empty
        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(baseContext, "Por favor complete los campos", Toast.LENGTH_SHORT)
                .show()
            return
        }

        //store the values in variables before validation with firebase auth
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, let move to the next activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Autenticación Correcta.", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Autenticación fallida.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Error ocurrido en ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}