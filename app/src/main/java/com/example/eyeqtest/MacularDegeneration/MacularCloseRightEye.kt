package com.example.eyeqtest.MacularDegeneration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.example.eyeqtest.Astigmatism.Astigmatism2
import com.example.eyeqtest.R

class MacularCloseRightEye : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_close_right_eye)

        val userChoice = intent.getIntExtra("firstChoice", 0)

        val continuebtn: Button = findViewById(R.id.cbCloseRightButton)

        continuebtn.setOnClickListener {
            val intent = Intent(this, MacularDegenerationTestRight::class.java)
            intent.putExtra("userChoice", userChoice)
            startActivity(intent)
        }
    }
}
