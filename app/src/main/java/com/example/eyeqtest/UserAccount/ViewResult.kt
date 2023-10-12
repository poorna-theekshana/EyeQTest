package com.example.eyeqtest.UserAccount

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.R

class ViewResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_result)

        val userId = intent.getStringExtra("id")

        val colorT = findViewById<Button>(R.id.color_test) // Use 'Button' instead of 'button'
        val asigT = findViewById<Button>(R.id.asig_test)
        val contrstT = findViewById<Button>(R.id.contrast_test)
        val macularT = findViewById<Button>(R.id.macular_test)

        colorT.setOnClickListener {
            val intent = Intent(this,ColorBlindProfileRecords::class.java)
            startActivity(intent)
        }
        asigT.setOnClickListener {
            val intent = Intent(this,AsigmatismRecords::class.java)
            startActivity(intent)
        }
        contrstT.setOnClickListener {
            val intent = Intent(this,ContrastSensitivityProfileRecords::class.java)
            startActivity(intent)
        }
        macularT.setOnClickListener {
            val intent = Intent(this,MacularRecords::class.java)
            startActivity(intent)
        }
    }
}
