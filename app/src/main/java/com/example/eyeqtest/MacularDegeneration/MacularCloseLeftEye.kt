package com.example.eyeqtest.MacularDegeneration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.ColorBlindTestLeft
import com.example.eyeqtest.R

class MacularCloseLeftEye : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_close_left_eye)

        val continueBtn = findViewById<Button>(R.id.cbCloseRightButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, MacularDegenerationTest::class.java)
            startActivity(intent)
        }
    }
}