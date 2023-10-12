package com.example.eyeqtest.Astigmatism

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class AstigmatismFail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism_fail)

        val closebtn: Button = findViewById(R.id.close_button)
        val shareButton = findViewById<Button>(R.id.share_button)

        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "Astigmatism Test Results\n You may be astigmatic."
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