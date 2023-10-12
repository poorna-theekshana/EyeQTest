package com.example.eyeqtest.MacularDegeneration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class MacularDegenerationResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_result)

        val back2Home = findViewById<Button>(R.id.normal_button2)
        val shareButton = findViewById<Button>(R.id.normal_button)

        back2Home.setOnClickListener {
            // Create an intent to start the MacularDegenerationTest activity
            val intent = Intent(this, MainActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "Check out my awesome results!"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    override fun onBackPressed() {
        // Lock back navigation
        // Add code to prevent the user from going back
    }
}