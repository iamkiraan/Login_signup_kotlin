package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class OwnerSignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_sign_in)
        auth = FirebaseAuth.getInstance()

        val Osigninemail = findViewById<TextView>(R.id.owneremail)
        val Osigninpassword = findViewById<TextView>(R.id.ownerpassword)
        val Osigninpassswordlayout = findViewById<TextInputLayout>(R.id.signinpasswordlayout)
        val Osigninprogress: ProgressBar = findViewById(R.id.ownerProgress)
        val Osignuptext = findViewById<TextView>(R.id.ownertextsignin)
        val Osigninbutton = findViewById<Button>(R.id.ownersignin)
        Osignuptext.setOnClickListener{
            val intent = Intent(this,ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        Osigninbutton.setOnClickListener {
            Osigninprogress.visibility = View.VISIBLE
            Osigninpassswordlayout.isPasswordVisibilityToggleEnabled = true
            val email = Osigninemail.text.toString()
            val password = Osigninpassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    Osigninemail.error = "enter your email Address"
                } else if (password.isEmpty()) {
                    Osigninpassswordlayout.isPasswordVisibilityToggleEnabled = false
                    Osigninpassword.error = "enter your password"
                }
                Osigninprogress.visibility = View.GONE
                Toast.makeText(this, "enter valid details", Toast.LENGTH_SHORT).show()
            } else if (!email.matches(emailPattern.toRegex())) {
                Osigninemail.error = "invalid email address"
                Osigninprogress.visibility = View.GONE
            } else if (password.length < 6) {
                Osigninpassswordlayout.isPasswordVisibilityToggleEnabled = false
                Osigninpassword.error = "enter password more than 6 characters"
                Osigninprogress.visibility = View.GONE
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, OwnerActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Please enter your valid email and password", Toast.LENGTH_SHORT).show()
                        Osigninprogress.visibility = View.GONE
                    }
                }
            }
        }

    }
}
