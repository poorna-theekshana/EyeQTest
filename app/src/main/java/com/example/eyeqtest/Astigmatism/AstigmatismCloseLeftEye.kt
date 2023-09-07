package com.example.eyeqtest.Astigmatism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.R

class AstigmatismCloseLeftEye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_left_eye)

        val continuebtn: Button = findViewById(R.id.cbCloseRightButton)

        continuebtn.setOnClickListener {
            val intent = Intent(this, Astigmatism::class.java)
            startActivity(intent)
        }
    }
}