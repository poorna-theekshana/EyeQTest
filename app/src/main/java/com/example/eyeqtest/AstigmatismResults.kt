package com.example.eyeqtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AstigmatismResults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism_results)

        val leftEyeResponse = intent.getStringExtra("leftEyeResponse")
        val rightEyeResponse = intent.getStringExtra("rightEyeResponse")

        val resultText = when {
            leftEyeResponse == "Normal" && rightEyeResponse == "Normal" -> "No Astigmatism"
            leftEyeResponse == "Blurred" && rightEyeResponse == "Blurred" -> "Possible Astigmatism"
            leftEyeResponse == "Normal" && rightEyeResponse == "Blurred" -> "Possible Astigmatism"
            leftEyeResponse == "Blurred" && rightEyeResponse == "Normal" -> "Possible Astigmatism"
            else -> "Inconclusive"
        }

        if (resultText == "No Astigmatism"){

        }
        val resultTextView: TextView = findViewById(R.id.textView2)
        resultTextView.text = resultText
    }
}