package com.example.eyeqtest.MacularDegeneration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeqtest.MainActivity
import com.example.eyeqtest.Modals.MacularTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MacularDegenerationResult : AppCompatActivity() {

    private lateinit var dbmessage: String
    private lateinit var time: String
    private lateinit var date: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var cbresult: MacularTestModal
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_macular_degeneration_result)

        dbRef = FirebaseDatabase.getInstance().getReference("MacularDegenerationTest")

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        date = "$year/$month/$day"
        time = "$hour.$minute"

        dbmessage = "You passed the test"

        uploaddata()


        val back2Home = findViewById<Button>(R.id.normal_button2)
        val shareButton = findViewById<Button>(R.id.normal_button)

        back2Home.setOnClickListener {
            // Create an intent to start the MacularDegenerationTest activity
            val intent = Intent(this, MainActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }
        shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareMessage = "Check out my awesome results!"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
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
            cbresult = MacularTestModal(
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