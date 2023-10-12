package com.example.eyeqtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase: DatabaseReference = FirebaseDatabase.getInstance().reference

        val imgHome: ImageButton = findViewById(R.id.homebutton)
        val imgNews: ImageButton = findViewById(R.id.newletterbutton)
        val imgUser: ImageButton = findViewById(R.id.userbutton)

        val fragmentHome = HomeFragment()
        val fragmentUser = UserFragment()
        val fragmentNews = NewsletterFragment()

        imgHome.setOnClickListener {
            imgHome.setImageResource(R.drawable.selected_home)
            imgUser.setImageResource(R.drawable.account_icon)
            imgNews.setImageResource(R.drawable.newsletter_icon)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView3, fragmentHome)
                commit()
            }
        }

        imgUser.setOnClickListener {
            imgHome.setImageResource(R.drawable.home_icon)
            imgUser.setImageResource(R.drawable.selected_user)
            imgNews.setImageResource(R.drawable.newsletter_icon)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView3, fragmentUser)
                commit()
            }
        }

        imgNews.setOnClickListener {
            imgHome.setImageResource(R.drawable.home_icon)
            imgUser.setImageResource(R.drawable.account_icon)
            imgNews.setImageResource(R.drawable.selected_newsletter)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView3, fragmentNews)
                commit()
            }
        }

        // Create a callback to handle back navigation
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Call lockBackNavigation on the current fragment
                val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3)
                if (currentFragment is HomeFragment) {
                    currentFragment.lockBackNavigation(true)
                }

            }
        }

        // Add the callback to the activity's onBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, callback)
    }
}