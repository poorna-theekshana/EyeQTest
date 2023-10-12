package com.example.eyeqtest.ContrastSensivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.Modals.ContrastSensivityTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ResultBadActivity : AppCompatActivity() {

    private var  correctAnswers = 0
    private lateinit var  dbmessage: String
    private lateinit var time: String
    private lateinit var date: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var cbresult: ContrastSensivityTestModal
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_bad)

        dbRef = FirebaseDatabase.getInstance().getReference("ContrastSensitivityTest")

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        date = "$year/$month/$day"
        time = "$hour.$minute"

        val imageView = findViewById<ImageView>(R.id.ic_cancel)
        imageView.setImageResource(R.drawable.ic_cancel)


         correctAnswers = intent.getIntExtra("correctAnswers", 0)

        val resultTextView = findViewById<TextView>(R.id.resultTextView2)
        resultTextView.text = "You got $correctAnswers out of 7 correct."

        dbmessage = "You got $correctAnswers out of 7 correct."

        uploaddata()

        val closebtn: Button = findViewById(R.id.normal_button2)
        val shareButton = findViewById<Button>(R.id.normal_button)

        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "You got $correctAnswers out of 7 correct."
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        closebtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        // Lock back navigation
        // Add code to prevent the user from going back
    }

    private fun uploaddata(){

        val results = dbmessage

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        var testId = ""

        user?.let {
            val uid = it.uid

            testId = dbRef.push().key!!
            cbresult = ContrastSensivityTestModal(
                uid,
                testId,
                date,
                time,
                results,
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
