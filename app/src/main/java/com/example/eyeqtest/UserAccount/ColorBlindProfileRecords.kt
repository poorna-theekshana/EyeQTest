package com.example.eyeqtest.UserAccount

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeqtest.Modals.ColorBlindTestAdapter
import com.example.eyeqtest.Modals.ColorBlindTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ColorBlindProfileRecords : AppCompatActivity() {

    private lateinit var cbresult: ArrayList<ColorBlindTestModal>
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recordRecyclerView: RecyclerView
    private lateinit var recordAdapter: ColorBlindTestAdapter // Create your custom adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_profile_records)

        cbresult = ArrayList()
        recordRecyclerView = findViewById(R.id.recordRecyclerView)
        recordRecyclerView.layoutManager = LinearLayoutManager(this)

        recordAdapter = ColorBlindTestAdapter(cbresult) // Create your custom adapter
        recordRecyclerView.adapter = recordAdapter

        getresults()
    }

    private fun getresults() {
        var userId = ""
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            userId = it.uid
        }
        dbRef = FirebaseDatabase.getInstance().getReference("ColorBlind")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cbresult.clear()
                if (snapshot.exists()) {
                    for (cbResultsSnap in snapshot.children) {
                        val cbResults = cbResultsSnap.getValue(ColorBlindTestModal::class.java)
                        if (cbResults?.userId == userId) {
                            cbresult.add(cbResults!!)
                        }
                    }
                    recordAdapter.notifyDataSetChanged() // Notify the adapter of data changes
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        })
    }
}
