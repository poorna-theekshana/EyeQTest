package com.example.eyeqtest.ColorBlindTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.R

class CloseRightEye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_right_eye)

        val continueBtn = findViewById<Button>(R.id.cbCloseRightButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, ColorBlindTestLeft::class.java)
            startActivity(intent)
        }
    }
}