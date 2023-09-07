package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.eyeqtest.R

class MacularCloseRightEye : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_close_right_eye)

        // Retrieve the userChoice variable from the previous activity
        var userChoice = intent.getIntExtra("userChoice", 0)

        val continuebtn: Button = findViewById(R.id.cbCloseRightButton)

        // Set click listener for the "cbCloseRightButton" button
        continuebtn.setOnClickListener {
            // Create an intent to start the MacularDegenerationTestRight activity
            val intent = Intent(this, MacularDegenerationTestRight::class.java)

            // Pass the userChoice variable to the next activity using extras
            intent.putExtra("userChoice", userChoice)

            // Start the new activity
            startActivity(intent)
        }
    }
}
