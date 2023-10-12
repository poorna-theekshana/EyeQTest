package com.example.eyeqtest.WelcomePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R
import com.example.eyeqtest.databinding.ActivitySignInPageBinding
import com.google.firebase.auth.FirebaseAuth

class SignInPage : AppCompatActivity() {
    private lateinit var binding: ActivitySignInPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_page)


        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.noAcc.setOnClickListener{
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
        binding.forgetPwd.setOnClickListener{
            val intent = Intent(this, ResetPasswordPage::class.java)
            startActivity(intent)
        }

        binding.logButton.setOnClickListener{
            val email = binding.logEmail.text.toString()
            val pass = binding.logPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                        val user = firebaseAuth.currentUser
                        user?.let {
                            // Name, email address, and profile photo Url
                            val name = it.displayName
                            val email = it.email
                            val photoUrl = it.photoUrl

                            // Check if user's email is verified
                            val emailVerified = it.isEmailVerified

                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use
                            // FirebaseUser.getIdToken() instead.
                            val uid = it.uid

                            println(name)
                            println(email)
                            println(photoUrl)
                            println(emailVerified)
                            println(uid)
                        }

                    } else {
                        Toast.makeText(this, "Email or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}