package com.example.eyeqtest.ContrastSensivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.eyeqtest.MainActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageView = findViewById<ImageView>(R.id.ic_check_circle)
        imageView.setImageResource(R.drawable.ic_check_circle)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)

        val resultTextView = findViewById<TextView>(R.id.resultTextView2)
        resultTextView.text = "You got $correctAnswers out of 7 correct."

        val closebtn: Button = findViewById(R.id.normal_button2)
        val shareButton = findViewById<Button>(R.id.normal_button)

        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "You got $correctAnswers out of 7 correct."
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        closebtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
