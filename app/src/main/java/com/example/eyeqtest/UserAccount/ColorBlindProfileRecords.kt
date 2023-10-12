package com.example.eyeqtest.UserAccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.eyeqtest.Modals.ColorBlindTestModal
import com.example.eyeqtest.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ColorBlindProfileRecords : AppCompatActivity() {

    private lateinit var cbresult: ArrayList<ColorBlindTestModal>
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var count: Number


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_profile_records)

        cbresult = arrayListOf<ColorBlindTestModal>()
        getresults()



    }
    private fun getresults(){
        var userId = ""
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.currentUser?.let {
            userId = it.uid
        }
        dbRef = FirebaseDatabase.getInstance().getReference("ColorBlind")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cbresult.clear()
                if (snapshot.exists()){
                    for (cbResultsSnap in snapshot.children){
                        val cbResults = cbResultsSnap.getValue(ColorBlindTestModal::class.java)
                        if(cbResults?.userId.equals(userId)) {
                            count = count as Int + 1
                            cbresult.add(cbResults!!)
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}
