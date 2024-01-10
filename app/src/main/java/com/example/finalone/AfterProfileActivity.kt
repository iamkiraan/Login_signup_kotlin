package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AfterProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_profile)
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener{
            val intenet = Intent(this,IntroActivity::class.java)
            startActivity(intent)
        }
    }
}