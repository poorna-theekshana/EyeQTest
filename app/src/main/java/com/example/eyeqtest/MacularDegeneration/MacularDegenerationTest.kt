package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.eyeqtest.R

class MacularDegenerationTest : AppCompatActivity() {

    private var userChoice: Int = 0 // Initialize the variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_test)

        val buttonNo = findViewById<Button>(R.id.btnNo)
        val buttonYes = findViewById<Button>(R.id.btnYes)

        buttonNo.setOnClickListener {
            userChoice = 1 // Assign 1 for "No"
            redirectToNextActivity()
        }

        buttonYes.setOnClickListener {
            Toast.makeText(this, "Your Right Eye Sight is Poor, Contact Your Ophthalmologist", Toast.LENGTH_SHORT).show()
            redirectToNextActivity()
        }
    }

    private fun redirectToNextActivity() {
       val intent = Intent(this, MacularCloseRightEye::class.java)
        intent.putExtra("userChoice", userChoice)
       startActivity(intent)
    }
}
