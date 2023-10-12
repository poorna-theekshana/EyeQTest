package com.example.eyeqtest.Astigmatism

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.Modals.AstigmatismTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class AstigmatismResults : AppCompatActivity() {

    private lateinit var dbmessage: String
    private lateinit var time: String
    private lateinit var date: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var cbresult: AstigmatismTestModal
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_astigmatism_results)

        dbRef = FirebaseDatabase.getInstance().getReference("AstigmatismTest")

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        date = "$year/$month/$day"
        time = "$hour.$minute"

        dbmessage = "Astigmatism Test Results\n" +
                " You seem to show no signs of Astigmatism in this test."

        uploaddata()

        val closebtn: Button = findViewById(R.id.close_button)
        val shareButton = findViewById<Button>(R.id.share_button)

        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "Astigmatism Test Results\n You seem to show no signs of Astigmatism in this test."
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
            cbresult = AstigmatismTestModal(
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