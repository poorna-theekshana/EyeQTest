package com.example.eyeqtest.ColorBlindTest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.eyeqtest.R

class ColorTestResults : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_test_results)

        val rightCorrectAnswers = intent.getIntExtra("rightcorrectAnswers", 0)
        val LeftCorrectAnswers = intent.getIntExtra("leftcorrectAnswers", 0)

        val resultTextLeft = findViewById<TextView>(R.id.cbLeftEyeResults)
        val resultTextRight = findViewById<TextView>(R.id.cbRightEyeResults)
        val btn = findViewById<Button>(R.id.cbResultsbtn)

        resultTextLeft .text = "You got $LeftCorrectAnswers out of 5 correct."
        resultTextRight.text = "You got $rightCorrectAnswers out of 5 correct."

        btn.setOnClickListener {
            val intent = Intent(this, ColorBlindHome::class.java)
            startActivity(intent)
        }

    }
}