package com.example.eyeqtest.ContrastSensivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class ResultBadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_bad)

        val imageView = findViewById<ImageView>(R.id.ic_cancel)
        imageView.setImageResource(R.drawable.ic_cancel)


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

    override fun onBackPressed() {
        // Lock back navigation
        // Add code to prevent the user from going back
    }
}
