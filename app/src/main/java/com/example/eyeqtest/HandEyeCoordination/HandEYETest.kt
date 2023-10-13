package com.example.eyeqtest.HandEyeCoordination

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eyeqtest.R

class HandEYETest : AppCompatActivity() {
    private lateinit var touch: ImageView
    private lateinit var constview: ConstraintLayout
    private var touchCount = 0
    private var touchStartTime: Long = SystemClock.elapsedRealtime() // Initialize with current time
    private var totalTime: Long = 0
    private var page = 0
    private var Righteye: Long = 0
    private var Lefteye: Long = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_eyetest)
        touch = findViewById(R.id.HItouch)
        constview = findViewById(R.id.HIconstraint)

        page = intent.getIntExtra("pgdirector", 0)
        Lefteye = intent.getLongExtra("lefteye",0)

        touch.setOnClickListener {
            val endtime = SystemClock.elapsedRealtime()
            if (touchCount < 5) {
                touchCount++
                val elapsedtimeMilliseconds = endtime - touchStartTime
                totalTime += elapsedtimeMilliseconds
                setRandomPosition()
                println("Event $touchCount - Elapsed time (milliseconds): $elapsedtimeMilliseconds - TotalTime: $totalTime")
                if (page == 0) {
                    Lefteye += elapsedtimeMilliseconds
                }
                if (page == 1) {
                    Righteye += elapsedtimeMilliseconds
                }
                if (touchCount == 5) {
                    if (page == 0) {
                        val intent = Intent(this, HiCloseLeftEye::class.java)
                        intent.putExtra("lefteye", Lefteye)
                        startActivity(intent)
                    }
                    if (page == 1) {
                        val intent = Intent(this, HandEyeCoResults::class.java)
                        intent.putExtra("righteye", Righteye)
                        intent.putExtra("leye",Lefteye)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun setRandomPosition() {
        val layoutParams = touch.layoutParams as ConstraintLayout.LayoutParams
        val layoutWidth = constview.width - touch.width
        val layoutHeight = constview.height - touch.height
        val randomX = (Math.random() * layoutWidth).toInt()
        val randomY = (Math.random() * layoutHeight).toInt()
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
