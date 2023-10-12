package com.example.eyeqtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.eyeqtest.Modals.UserModal
import com.example.eyeqtest.UserAccount.ViewResult
import com.example.eyeqtest.WelcomePage.SignInPage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class UserFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var usernametxt: TextView
    private lateinit var email: TextView
    private lateinit var contactNumb: TextView

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        var userId = ""
        usernametxt = view.findViewById(R.id.usernametxt)
        email = view.findViewById(R.id.useremail)
        contactNumb = view.findViewById(R.id.usertele)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        user?.let {
            userId = it.uid

            database = FirebaseDatabase.getInstance().getReference("users").child(userId)
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        val userData = snapshot.getValue(UserModal::class.java)
                        email.text = userData?.email
                        usernametxt.text = userData?.username
                        contactNumb.text = userData?.phoneNumb
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        val viewResultsBtn = view.findViewById<Button>(R.id.viewResults)
        viewResultsBtn.setOnClickListener {
          val intent = Intent(activity,ViewResult::class.java)
            intent.putExtra("id",userId)
            startActivity(intent)
        }

        val logoutBtn = view.findViewById<Button>(R.id.logout_btn)
        logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity, SignInPage::class.java)
            startActivity(intent)
            activity?.finish()
        }




        return view
    }

}