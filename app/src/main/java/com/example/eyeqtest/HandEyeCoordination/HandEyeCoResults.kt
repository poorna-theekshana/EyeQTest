package com.example.eyeqtest.HandEyeCoordination

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.eyeqtest.R

class HandEyeCoResults : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_eye_co_results)

        val rnum = intent.getLongExtra("righteye",0)
        val lnum = intent.getLongExtra("lefteye",0)

        val rightresult = findViewById<TextView>(R.id.RightEyeHIResult)
        val leftresult = findViewById<TextView>(R.id.LeftEyeHIResult)

        rightresult.text = "$rnum"
        leftresult.text = "$lnum"
    }
}