package com.example.eyeqtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeqtest.Adapter.NewsletterAdapter
import com.example.eyeqtest.Modals.NewsletterModal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NewsletterFragment : Fragment() {
    private lateinit var newsletter: ArrayList<NewsletterModal>
    private lateinit var dbRef: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newsletter, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        newsletter = ArrayList()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = NewsletterAdapter(newsletter)
        recyclerView.adapter = adapter

        getresults()

        return view
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
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error here
            }
        })
    }
}
