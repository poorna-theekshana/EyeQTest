package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.eyeqtest.R

class MacularDegenerationTest : AppCompatActivity() {

    private var userChoice: Int = 0 // Initialize the variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_test)

        val buttonNo = findViewById<Button>(R.id.No)
        val buttonYes = findViewById<Button>(R.id.Yes)

        buttonNo.setOnClickListener {
            userChoice = 1 // Assign 1 for "No"
            redirectToNextActivity()
        }

        buttonYes.setOnClickListener {

            redirectToNextActivity()
        }
    }

    private fun redirectToNextActivity() {
        // Create an intent to start the MacularCloseRightEye activity
        val intent = Intent(this, MacularCloseRightEye::class.java)

        // Pass the userChoice variable to the next activity using extras
        intent.putExtra("userChoice", userChoice)

        // Start the new activity
        startActivity(intent)
    }
}
