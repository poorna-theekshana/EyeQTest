package com.example.eyeqtest.ColorBlindTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class ColorBlindHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_home)

        val continueBtn = findViewById<Button>(R.id.cbStartTestButton)
        val cancelbtn = findViewById<Button>(R.id.cbCancelButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, CloseRightEye::class.java)
            startActivity(intent)
        }

        cancelbtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}