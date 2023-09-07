package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.eyeqtest.R

class MacularDegenerationTestRight : AppCompatActivity() {

    private var userChoice: Int = 0 // Initialize the variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_test)

        val buttonNo = findViewById<Button>(R.id.No)
        val buttonYes = findViewById<Button>(R.id.Yes)

        // Retrieve the userChoice variable from the previous activity
        userChoice = intent.getIntExtra("userChoice", 0)

        // Set click listeners for the "No" and "Yes" buttons
        buttonNo.setOnClickListener {
            // Increase the userChoice variable by 1 for "No"
            userChoice++

            // Check if the variable value is 2
            if (userChoice >= 2) {
                // Redirect to MacularDegenerationResult activity
                redirectToResultActivity()
            }
        }

        buttonYes.setOnClickListener {
            // No need to change the userChoice, it remains 0 for "Yes"

            // Check if the variable value is 2
            if (userChoice >= 2) {
                // Redirect to MacularDegenerationResult activity
                redirectToResultActivity()
            }
        }
    }

    private fun redirectToResultActivity() {
        val intent = Intent(this, MacularDegenerationResult::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (userChoice < 2) {
            Toast.makeText(this, "You Should Consult a Doctor", Toast.LENGTH_SHORT).show()
        }
        super.onBackPressed()
    }
}
