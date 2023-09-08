package com.example.eyeqtest.ContrastSensivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.R

class ContrastSensivityFront : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrast_sensitivity_front)


        val continueBtn = findViewById<Button>(R.id.csStartTestButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, ContrastSensivityActivity::class.java)
            startActivity(intent)
        }
    }


}