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

        // Retrieve the userChoice variable from the previous activity for both eyes
        val firstChoice = intent.getIntExtra("firstChoice", 0)
        val secondChoice = intent.getIntExtra("userChoice", 0)

        // Set click listeners for the "No" and "Yes" buttons
        buttonNo.setOnClickListener {
            // If both eyes are bad, show the bad result
            if (firstChoice == 1 && secondChoice == 1) {
                redirectToResultActivity("bad")
            } else {
                // Otherwise, go to the fail activity
                redirectToFailActivity()
            }
        }

        buttonYes.setOnClickListener {
            // If both eyes are good, show the good result
            if (firstChoice == 0 && secondChoice == 0) {
                redirectToResultActivity("good")
            } else {
                // Otherwise, go to the fail activity
                redirectToFailActivity()
            }
        }
    }

    private fun redirectToResultActivity(result: String) {
        val intent = Intent(this, MacularDegenerationResult::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }

    private fun redirectToFailActivity() {
        val intent = Intent(this, MacularDegenerationFail::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        redirectToFailActivity()
    }
}
