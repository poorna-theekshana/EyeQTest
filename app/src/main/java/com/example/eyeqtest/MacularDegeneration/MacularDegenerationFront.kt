package com.example.eyeqtest.MacularDegeneration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.eyeqtest.R

class MacularDegenerationFront : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_front)

        val startTestButton = findViewById<View>(R.id.start_test_button)

        // Set an OnClickListener for the button
        startTestButton.setOnClickListener {
            // Create an intent to start the MacularDegenerationTest activity
            val intent = Intent(this, MacularCloseLeftEye::class.java)

            // Start the new activity
            startActivity(intent)
        }
    }
}