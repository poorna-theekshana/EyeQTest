package com.example.eyeqtest.WelcomePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.R
import com.example.eyeqtest.databinding.ActivityWelcomePageBinding
import com.google.firebase.auth.FirebaseAuth

class WelcomePage : AppCompatActivity() {

    private lateinit var signin : Button
    private lateinit var signup : Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        firebaseAuth = FirebaseAuth.getInstance()

        signin=findViewById(R.id.signinbtn)
        signup=findViewById(R.id.signupbtn)

        signin.setOnClickListener {
            val intent = Intent(this,SignInPage::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener {
            val intent = Intent(this,SignUpPage::class.java)
            startActivity(intent)
        }






    }
}