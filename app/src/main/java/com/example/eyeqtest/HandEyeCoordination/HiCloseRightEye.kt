package com.example.eyeqtest.HandEyeCoordination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.ColorBlindTestLeft
import com.example.eyeqtest.R

class HiCloseRightEye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_close_right_eye)

        val continueBtn = findViewById<Button>(R.id.cbCloseRightButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, HandEYETest::class.java)
            startActivity(intent)
        }
    }
}