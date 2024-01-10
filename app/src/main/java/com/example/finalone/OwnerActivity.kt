package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class OwnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner)
        val text = findViewById<TextView>(R.id.textView8)
        val profile = findViewById<ImageView>(R.id.imageView4)
        profile.setOnClickListener{
            val intent =  Intent(this,OwnerProfileActivity::class.java)
      startActivity(intent)
        }
    }
}