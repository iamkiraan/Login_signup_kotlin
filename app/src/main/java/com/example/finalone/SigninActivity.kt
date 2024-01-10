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

class SigninActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        auth = FirebaseAuth.getInstance()

        val signinemail = findViewById<TextView>(R.id.signinemail)
        val signinpassword = findViewById<TextView>(R.id.signinpassword)
        val signinpassswordlayout = findViewById<TextInputLayout>(R.id.signinpasswordlayout)
        val signinprogress: ProgressBar = findViewById(R.id.signinprogress)
        val signuptext = findViewById<TextView>(R.id.signinbutton)
        val signinbutton = findViewById<Button>(R.id.signinButton1)
        signuptext.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
        signinbutton.setOnClickListener {
            signinprogress.visibility = View.VISIBLE
            signinpassswordlayout.isPasswordVisibilityToggleEnabled = true
            val email = signinemail.text.toString()
            val password = signinpassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    signinemail.error = "enter your email Address"
                } else if (password.isEmpty()) {
                    signinpassswordlayout.isPasswordVisibilityToggleEnabled = false
                    signinpassword.error = "enter your password"
                }
                signinprogress.visibility = View.GONE
                Toast.makeText(this, "enter valid details", Toast.LENGTH_SHORT).show()
            } else if (!email.matches(emailPattern.toRegex())) {
                signinemail.error = "invalid email address"
                signinprogress.visibility = View.GONE
            } else if (password.length < 6) {
                signinpassswordlayout.isPasswordVisibilityToggleEnabled = false
                signinpassword.error = "enter password more than 6 characters"
                signinprogress.visibility = View.GONE
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Please enter your valid email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                        signinprogress.visibility = View.GONE
                    }
                }
            }
        }

    }
}
