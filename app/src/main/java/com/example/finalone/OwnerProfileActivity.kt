package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.finalone.databinding.ActivityOwnerProfileBinding
import com.example.finalone.databinding.ActivityUserProfBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

class OwnerProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOwnerProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_owner_profile)
        val email = findViewById<TextView>(R.id.email)
        val email1 = findViewById<TextView>(R.id.emil1)
        val userName = findViewById<TextView>(R.id.name)
        val phone = findViewById<TextView>(R.id.phone)
        val phone1 = findViewById<TextView>(R.id.phone1)
    }

}