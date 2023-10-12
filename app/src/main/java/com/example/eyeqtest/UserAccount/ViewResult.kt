package com.example.eyeqtest.UserAccount

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
    }
}
