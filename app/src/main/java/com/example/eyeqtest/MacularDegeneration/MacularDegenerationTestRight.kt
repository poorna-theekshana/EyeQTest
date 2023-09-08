package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.eyeqtest.Astigmatism.AstigmatismFail
import com.example.eyeqtest.Astigmatism.AstigmatismResults
import com.example.eyeqtest.R

class MacularDegenerationTestRight : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_test)

        val buttonNo = findViewById<Button>(R.id.btnNo)
        val buttonYes = findViewById<Button>(R.id.btnYes)

        // Retrieve the userChoice variable from the previous activity
        val userChoice = intent.getIntExtra("userChoice", 0)

        // Set click listeners for the "No" and "Yes" buttons
        buttonNo.setOnClickListener {
            val secondChoice = userChoice + 1

            if (secondChoice == 1) {
                redirectToResultActivity()
            } else {
                onBackPressed()
            }
        }

        buttonYes.setOnClickListener {
            onBackPressed()
        }
    }
    private fun redirectToResultActivity() {
        val intent = Intent(this, MacularDegenerationResult::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MacularDegenerationFail::class.java)
        startActivity(intent)
    }
}
