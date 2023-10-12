package com.example.eyeqtest.ColorBlindTest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.Modals.ColorBlindTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ColorTestResults : AppCompatActivity() {
    private var  rightCorrectAnswers = 0
    private var LeftCorrectAnswers =0
    private lateinit var time: String
    private lateinit var date: String
    private lateinit var Rcomment: String
    private lateinit var Lcomment: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var cbresult: ColorBlindTestModal
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_test_results)

        dbRef = FirebaseDatabase.getInstance().getReference("ColorBlind")

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        date = "$year/$month/$day"
        time = "$hour.$minute"

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

        uploaddata()

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

            Rcomment = "Poor"
            rightemo.setImageResource(R.drawable.poor_emo)
            rightgrade.setTextColor(resources.getColor(R.color.red))
            rightgrade.text = "Poor"
        }
        else if(rightCorrectAnswers <= 4){
            val rightgrade = findViewById<TextView>(R.id.cbresultActionR)
            val rightemo = findViewById<ImageView>(R.id.cbResultEmoR)

            Rcomment = "Average"
            rightemo.setImageResource(R.drawable.average_emo)
            rightgrade.setTextColor(resources.getColor(R.color.yellow))
            rightgrade.text = "Average"
        }
        else{
            val rightgrade = findViewById<TextView>(R.id.cbresultActionR)
            val rightemo = findViewById<ImageView>(R.id.cbResultEmoR)

            Rcomment = "Excellent"
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

            Lcomment = "Poor"
            leftemo.setImageResource(R.drawable.poor_emo)
            leftgrade.setTextColor(resources.getColor(R.color.red))
            leftgrade.text = "Poor"
        }
        else if(LeftCorrectAnswers <= 4){
            val leftgrade = findViewById<TextView>(R.id.cbresultActionL)
            val leftemo = findViewById<ImageView>(R.id.cbResultEmoL)

            Lcomment = "Average"
            leftemo.setImageResource(R.drawable.average_emo)
            leftgrade.setTextColor(resources.getColor(R.color.yellow))
            leftgrade.text = "Average"
        }
        else{
            val leftgrade = findViewById<TextView>(R.id.cbresultActionL)
            val leftemo = findViewById<ImageView>(R.id.cbResultEmoL)

            Lcomment = "Excellent"
            leftemo.setImageResource(R.drawable.excellent_emo)
            leftgrade.setTextColor(resources.getColor(R.color.green))
            leftgrade.text = "Excellent"
        }

    }
    private fun uploaddata(){

        val Rresults = rightCorrectAnswers.toString()
        val Lresults = LeftCorrectAnswers.toString()

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        var testId = ""

        user?.let {
            val uid = it.uid

            testId = dbRef.push().key!!
            cbresult = ColorBlindTestModal(
                uid,
                testId,
                date,
                time,
                Rresults,
                Rcomment,
                Lresults,
                Lcomment
            )

            dbRef.child(testId).setValue(cbresult)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }
}