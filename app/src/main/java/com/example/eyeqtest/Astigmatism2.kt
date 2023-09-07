package com.example.eyeqtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.eyeqtest.MacularDegeneration.MacularDegenerationResult

class Astigmatism2 : AppCompatActivity() {

    private var firstChoice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism)

        val normalbtn = findViewById<Button>(R.id.normal_button)
        val blurredbtn = findViewById<Button>(R.id.blurred_button)

        firstChoice = intent.getIntExtra("firstChoice", 0)

        normalbtn.setOnClickListener {
            firstChoice++

            if (firstChoice == 2) {
                redirectToResultActivity()
            } else {
                onBackPressed()
            }
        }

        blurredbtn.setOnClickListener {
            onBackPressed()
        }
    }
    private fun redirectToResultActivity() {
        val intent = Intent(this, MacularDegenerationResult::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "You Should Consult a Doctor", Toast.LENGTH_SHORT).show()
    }
}