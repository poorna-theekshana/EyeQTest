package com.example.eyeqtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.ColorBlindTestRight

class AstigmatismCloseRightEye : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_right_eye)

        val firstChoice = intent.getIntExtra("firstChoice", 0)

        val continuebtn: Button = findViewById(R.id.cbCloseRightButton)

        continuebtn.setOnClickListener {
            val intent = Intent(this, Astigmatism2::class.java)
            intent.putExtra("firstChoice", firstChoice)
            startActivity(intent)
        }
    }
}