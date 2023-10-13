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
        val lnum = intent.getLongExtra("leye",0)

//        rnum.toDouble()
//        lnum.toDouble()

        val rightresult = findViewById<TextView>(R.id.RightEyeHIResult)
        val leftresult = findViewById<TextView>(R.id.LeftEyeHIResult)

        val righteyesec = (rnum / 1000.0)
        val lefteyesec = (lnum / 1000.0)

        rightresult.text = "$righteyesec"
        leftresult.text = "$lefteyesec "
    }
}