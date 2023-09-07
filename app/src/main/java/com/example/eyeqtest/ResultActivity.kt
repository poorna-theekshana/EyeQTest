package com.example.eyeqtest

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = "You got $correctAnswers out of 7 correct."
    }
}
