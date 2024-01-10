package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var ePassword :EditText
    private lateinit var eButton : Button
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
       ePassword = findViewById(R.id.signupemail)
        eButton = findViewById(R.id.button10)
        val betton = findViewById<Button>(R.id.button11)
        auth = FirebaseAuth.getInstance()
        eButton.setOnClickListener{
            val sPassword = ePassword.text.toString()
            auth.sendPasswordResetEmail(sPassword).addOnCompleteListener{
                Toast.makeText(this,"please check your email",Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener{
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }



        }
        betton.setOnClickListener{
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }

    }
}