package com.example.eyeqtest.ColorBlindTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.R

class CloseLeftEye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_left_eye)

        val leftCorrectAnswers = intent.getIntExtra("correctAnswers", 0)
        val continuebtn: Button = findViewById(R.id.cbCloseRightButton)

        continuebtn.setOnClickListener {
            val intent = Intent(this, ColorBlindTestRight::class.java)
            intent.putExtra("correctAnswers", leftCorrectAnswers)
            startActivity(intent)
        }
    }
}