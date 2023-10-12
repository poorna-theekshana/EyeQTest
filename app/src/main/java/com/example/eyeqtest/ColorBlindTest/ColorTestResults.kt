package com.example.eyeqtest.ColorBlindTest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.R

class ColorTestResults : AppCompatActivity() {
    private var  rightCorrectAnswers = 0
    private var LeftCorrectAnswers =0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_test_results)

        rightCorrectAnswers = intent.getIntExtra("rightcorrectAnswers", 0)
        LeftCorrectAnswers = intent.getIntExtra("leftcorrectAnswers", 0)

        displayresultsleft()
        displayresultsright()

        val resultTextLeft = findViewById<TextView>(R.id.cbLeftEyeResults)
        val resultTextRight = findViewById<TextView>(R.id.cbRightEyeResults)
        val btn = findViewById<Button>(R.id.cbResultsbtn)
        val sharebtn = findViewById<Button>(R.id.cbshareResults)

        resultTextLeft .text = "$LeftCorrectAnswers/5"
        resultTextRight.text = "$rightCorrectAnswers/5"

        btn.setOnClickListener {
            val intent = Intent(this, ColorBlindHome::class.java)
            startActivity(intent)
        }

        sharebtn.setOnClickListener{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val shareMessage = "Color Vision Test Results\nRight eye score $rightCorrectAnswers/5\nleft eye score $LeftCorrectAnswers/5"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        }
    private fun displayresultsright(){

        if(rightCorrectAnswers <= 2){
            val rightgrade = findViewById<TextView>(R.id.cbresultActionR)
            val rightemo = findViewById<ImageView>(R.id.cbResultEmoR)

            rightemo.setImageResource(R.drawable.poor_emo)
            rightgrade.setTextColor(resources.getColor(R.color.red))
            rightgrade.text = "Poor"
        }
        else if(rightCorrectAnswers <= 4){
            val rightgrade = findViewById<TextView>(R.id.cbresultActionR)
            val rightemo = findViewById<ImageView>(R.id.cbResultEmoR)

            rightemo.setImageResource(R.drawable.average_emo)
            rightgrade.setTextColor(resources.getColor(R.color.yellow))
            rightgrade.text = "Average"
        }
        else{
            val rightgrade = findViewById<TextView>(R.id.cbresultActionR)
            val rightemo = findViewById<ImageView>(R.id.cbResultEmoR)

            rightemo.setImageResource(R.drawable.excellent_emo)
            rightgrade.setTextColor(resources.getColor(R.color.green))
            rightgrade.text = "Excellent"
        }

    }

    override fun onBackPressed() {
        // Lock back navigation
        // Add code to prevent the user from going back
    }

    private fun displayresultsleft(){

        if(LeftCorrectAnswers <= 2){
            val leftgrade = findViewById<TextView>(R.id.cbresultActionL)
            val leftemo = findViewById<ImageView>(R.id.cbResultEmoL)

            leftemo.setImageResource(R.drawable.poor_emo)
            leftgrade.setTextColor(resources.getColor(R.color.red))
            leftgrade.text = "Poor"
        }
        else if(LeftCorrectAnswers <= 4){
            val leftgrade = findViewById<TextView>(R.id.cbresultActionL)
            val leftemo = findViewById<ImageView>(R.id.cbResultEmoL)

            leftemo.setImageResource(R.drawable.average_emo)
            leftgrade.setTextColor(resources.getColor(R.color.yellow))
            leftgrade.text = "Average"
        }
        else{
            val leftgrade = findViewById<TextView>(R.id.cbresultActionL)
            val leftemo = findViewById<ImageView>(R.id.cbResultEmoL)

            leftemo.setImageResource(R.drawable.excellent_emo)
            leftgrade.setTextColor(resources.getColor(R.color.green))
            leftgrade.text = "Excellent"
        }

    }
}