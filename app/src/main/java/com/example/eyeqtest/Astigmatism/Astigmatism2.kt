package com.example.eyeqtest.Astigmatism

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.eyeqtest.MacularDegeneration.MacularDegenerationResult
import com.example.eyeqtest.R

class Astigmatism2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism)

        val normalbtn = findViewById<Button>(R.id.normal_button)
        val blurredbtn = findViewById<Button>(R.id.blurred_button)

        val firstChoice = intent.getIntExtra("firstChoice", 0)

        normalbtn.setOnClickListener {
            val secondChoice = firstChoice + 1

            if (secondChoice == 2) {
                redirectToResultActivity()
            } else {
                onBackPressed()
            }
        }

        blurredbtn.setOnClickListener {
            onBackPressed()
        }
    }
    private fun redirectToResultActivity() {
        val intent = Intent(this, AstigmatismResults::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, AstigmatismFail::class.java)
        startActivity(intent)
    }
}