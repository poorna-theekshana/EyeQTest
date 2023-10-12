package com.example.eyeqtest.HandEyeCoordination

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eyeqtest.R

class HandEYETest : AppCompatActivity() {
    private lateinit var touch: ImageView
    private lateinit var constview: ConstraintLayout
    private var touchCount = 0
    private var touchStartTime : Long = 0
    private var totalTime : Long = 0
    private var page = 0
    private var Righteye : Long = 0
    private var Lefteye : Long = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_eyetest)
        touch = findViewById(R.id.HItouch)
        constview = findViewById(R.id.HIconstraint)


        page = intent.getIntExtra("pgdirector",0)
        Righteye = intent.getLongExtra("righteye",Righteye)

        if(touchCount == 0){
            touchStartTime = SystemClock.elapsedRealtime()
        }



        touch.setOnClickListener {
            val endtime = SystemClock.elapsedRealtime()
            if(touchCount < 5){
                touchCount++
                val elapsedtime = touchStartTime-endtime
                totalTime += elapsedtime
                setRandomPosition()
            }
            if(touchCount >= 5){
                touchCount = 0
                if(page == 0){
                    Righteye = (totalTime / 1000)/5
                    val intent = Intent(this,HiCloseLeftEye::class.java)
                    intent.putExtra("righteye",Righteye)
                    startActivity(intent)
                }
                if(page == 1){
                    Lefteye = (totalTime / 1000)/5
                    val intent = Intent(this,HandEyeCoResults::class.java)
                    intent.putExtra("lefteye",Lefteye)
                    intent.putExtra("righteye",Righteye)
                    startActivity(intent)
                }

            }

        }

    }
    private fun setRandomPosition(){

        val layoutParams = touch.layoutParams as ConstraintLayout.LayoutParams

        val layoutWidth = constview.width - touch.width
        val layoutHeight = constview.height - touch.height

        // Calculate random X and Y coordinates
        val randomX = (Math.random() * layoutWidth).toInt()
        val randomY = (Math.random() * layoutHeight).toInt()

        // Update the ImageView's position within the ConstraintLayout
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.topMargin = randomY
        layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.leftMargin = randomX
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID

        touch.layoutParams = layoutParams
        constview.requestLayout()

        touchStartTime = SystemClock.elapsedRealtime()

    }
}