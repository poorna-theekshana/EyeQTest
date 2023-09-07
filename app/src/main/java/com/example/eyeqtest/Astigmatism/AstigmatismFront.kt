package com.example.eyeqtest.Astigmatism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class AstigmatismFront : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism_front)

        val startbtn: Button = findViewById(R.id.start_test_button)

        startbtn.setOnClickListener {
            val intent = Intent(this, AstigmatismCloseLeftEye::class.java)
            startActivity(intent)
        }

        val closebtn: Button = findViewById(R.id.close_button)

        closebtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}