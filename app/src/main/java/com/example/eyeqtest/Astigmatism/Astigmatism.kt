package com.example.eyeqtest.Astigmatism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.MacularDegeneration.MacularCloseRightEye
import com.example.eyeqtest.R

class Astigmatism : AppCompatActivity() {
    private var firstChoice: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism)

        val normalbtn = findViewById<Button>(R.id.normal_button)
        val blurredbtn = findViewById<Button>(R.id.blurred_button)

        normalbtn.setOnClickListener {
            firstChoice = 1
            redirectToNextActivity()
        }

        blurredbtn.setOnClickListener {
            redirectToNextActivity()
        }
    }
    private fun redirectToNextActivity() {
        val intent = Intent(this, AstigmatismCloseRightEye::class.java)
        intent.putExtra("firstChoice", firstChoice)
        startActivity(intent)
    }
}