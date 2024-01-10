package com.example.finalone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val signupname = findViewById<EditText>(R.id.signupname)
        val signupemail = findViewById<EditText>(R.id.signupemail)
        val signuppassword = findViewById<EditText>(R.id.signuppassword)
        val signupphone = findViewById<EditText>(R.id.signupphonenumber)
        val signupcpassword = findViewById<EditText>(R.id.signupconfirmpassword)
        val signUpButton = findViewById<Button>(R.id.signupbutton)
        val signIntext = findViewById<TextView>(R.id.signInB)
        val signuppasswordlayout = findViewById<TextInputLayout>(R.id.signuppasswordlayout)
        val signupcpasswordlayout = findViewById<TextInputLayout>(R.id.signupcpasswordlayout)
        val signupProgressbar = findViewById<ProgressBar>(R.id.signupprogress)


        signIntext.setOnClickListener() {
            val intent = Intent(this, FinalSignInActivity::class.java)
            startActivity(intent)
        }
        signUpButton.setOnClickListener() {
            val name = signupname.text.toString()
            val email = signupemail.text.toString()
            val phone = signupphone.text.toString()
            val password = signuppassword.text.toString()
            val cpassword = signupcpassword.text.toString()
            signupProgressbar.visibility = View.VISIBLE
            signuppasswordlayout.isPasswordVisibilityToggleEnabled = true
            signupcpasswordlayout.isPasswordVisibilityToggleEnabled = true
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                if (name.isEmpty()) {
                    signupname.error = "enter your name"
                }
                if (email.isEmpty()) {
                    signupemail.error = "enter your email address"
                }
                if (phone.isEmpty()) {
                    signupphone.error = "enter your phone number "
                }
                if (password.isEmpty()) {
                    signuppasswordlayout.isPasswordVisibilityToggleEnabled = false
                    signuppassword.error = "enter password"

                }
                if (cpassword.isEmpty()) {
                    signupcpasswordlayout.isPasswordVisibilityToggleEnabled = false
                    signupcpassword.error = "Renter your password"

                }
                Toast.makeText(this, "Enter valid Details", Toast.LENGTH_SHORT).show()
                signupProgressbar.visibility = View.GONE
            } else if (!email.matches(emailPattern.toRegex())) {
                signupemail.error = "invalid email adrdress"
                signupProgressbar.visibility = View.GONE
            } else if (phone.length != 10) {
                signupphone.error = "invalid phone  number"
                signupProgressbar.visibility = View.GONE
            } else if (password.length < 6) {
                signuppasswordlayout.isPasswordVisibilityToggleEnabled = false
                signuppassword.error = "enter password more than 6 characters"
                signupProgressbar.visibility = View.GONE
            } else if (cpassword != password) {
                signupcpasswordlayout.isPasswordVisibilityToggleEnabled = false
                signupProgressbar.visibility = View.GONE
                signupcpassword.error = "password not matched"
                Toast.makeText(this, "password not matched", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val databaseRef = database.reference.child(auth.currentUser!!.uid)
                        val users:Users = Users(name,email,phone,auth.currentUser!!.uid)
                        databaseRef.setValue(users).addOnCompleteListener{
                            if(it.isSuccessful){


                                val profileIntent = Intent(this, OwnerProfileActivity::class.java).apply {
                                    putExtra("username", name)
                                    putExtra("email",email)
                                    putExtra("phoneNumber", phone)
                                }

                                startActivity(profileIntent)


                                val signInIntent = Intent(this,ChooseActivity::class.java)
                                startActivity(signInIntent)


                                finish()



                            }else{
                                Toast.makeText(this, "something went wrong,try again", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "something went wrong,try again", Toast.LENGTH_SHORT)
                            .show()
                    }


                }

            }
        }
    }
}