package com.example.eyeqtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eyeqtest.Modals.ColorBlindTestModal
import com.example.eyeqtest.Modals.NewsletterModal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class NewsletterFragment : Fragment() {
    private lateinit var newsletter: ArrayList<NewsletterModal>
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsletter, container, false)

        getresults()

    }
    private fun getresults() {
        firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Newsletter")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                newsletter.clear()
                if (snapshot.exists()) {
                    for (newsletterSnap in snapshot.children) {
                        val newsletterL = newsletterSnap.getValue(NewsletterModal::class.java)
                            newsletter.add(newsletterL!!)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        })
    }
}