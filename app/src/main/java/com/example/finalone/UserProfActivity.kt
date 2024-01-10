package com.example.finalone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView

class UserProfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_prof)
        // Inside your ProfileActivity.kt
        val PICK_IMAGE_REQUEST = 1

// ...

        val profileImageView: ImageView = findViewById(R.id.profileImageView)

        profileImageView.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
        }

        // Handle the result from the image picker
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
                val selectedImageUri: Uri = data.data!!
                profileImageView.setImageURI(selectedImageUri)
            }
        }

    }
}