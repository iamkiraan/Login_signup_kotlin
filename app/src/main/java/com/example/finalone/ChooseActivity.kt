package com.example.finalone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        val owner =  findViewById<Button>(R.id.button3)
        val user = findViewById<Button>(R.id.button4)
        user.setOnClickListener{
            saveUserChoice("user")
          navigateToSigninACtivity()
        }
        owner.setOnClickListener{
            saveUserChoice("owner")
            navigateToSigninACtivity()
        }

    }

    private fun saveUserChoice(choice: String) {
        val sharedPreferences = getSharedPreferences("UserChoice",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("role",choice)
        editor.apply()

    }

    private fun navigateToSigninACtivity() {
        val intent = Intent(this,FinalSignInActivity::class.java)
        startActivity(intent)
    }
}