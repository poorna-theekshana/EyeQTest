package com.example.eyeqtest.HandEyeCoordination

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.CloseRightEye
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.R

class HandEyeCoordinationHome : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_eye_coordination_home)

        val continueBtn = findViewById<Button>(R.id.hiStartTestButton)
        val cancelbtn = findViewById<Button>(R.id.hiCancelButton)

        continueBtn.setOnClickListener {
            val intent = Intent(this, HiCloseRightEye::class.java)
            startActivity(intent)
        }

        cancelbtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}