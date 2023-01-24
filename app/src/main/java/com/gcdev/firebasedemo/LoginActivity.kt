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

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val login_to_register: TextView = findViewById(R.id.textView_Login_to_Register)

        //create account text
        login_to_register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnLogin: Button = findViewById(R.id.button_Login)

        btnLogin.setOnClickListener{
            performLogin()
        }

    }

    private fun performLogin() {
        val email: EditText = findViewById(R.id.editTextEmail_Login)
        val password: EditText = findViewById(R.id.editPassword_Login)

        //verify that the form is empty
        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT)
                .show()
            return
        }

        //store the values in variables before validation with firebase auth
        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, go to the main activity page
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Autenticacion Correcta", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Autenticacion Fallida",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Autenticacion Fallida ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}