package com.example.eyeqtest.blinkingrouting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.Astigmatism.AstigmatismCloseLeftEye
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class BlinkingFront : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blinking_front)

        val startblink: Button = findViewById(R.id.start_blink)

        startblink.setOnClickListener {
            val intent = Intent(this, EyesActivity::class.java)
            startActivity(intent)
        }

        val closeblink: Button = findViewById(R.id.close_blink)

        closeblink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}