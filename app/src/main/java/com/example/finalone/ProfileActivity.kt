package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val image = findViewById<Button>(R.id.button)
        image.setOnClickListener{
            val intent = Intent(this,AfterProfileActivity::class.java)
            startActivity(intent)
        }

    }
}