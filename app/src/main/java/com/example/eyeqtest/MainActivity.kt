package com.example.eyeqtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                replace(R.id.mainfragment, fragmentHome)
                commit()
            }
        }

        imgUser.setOnClickListener {
            imgHome.setImageResource(R.drawable.home_icon)
            imgUser.setImageResource(R.drawable.selected_user)
            imgNews.setImageResource(R.drawable.newsletter_icon)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainfragment, fragmentUser)
                commit()
            }
        }

        imgNews.setOnClickListener {
            imgHome.setImageResource(R.drawable.home_icon)
            imgUser.setImageResource(R.drawable.account_icon)
            imgNews.setImageResource(R.drawable.selected_newsletter)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.mainfragment, fragmentNews)
                commit()
            }
        }

    }
}