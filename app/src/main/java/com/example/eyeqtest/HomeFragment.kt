package com.example.eyeqtest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eyeqtest.Astigmatism.AstigmatismFront
import com.example.eyeqtest.ColorBlindTest.ColorBlindHome
import com.example.eyeqtest.ContrastSensivity.ContrastSensivityFront
import com.example.eyeqtest.HandEyeCoordination.HandEyeCoordinationHome
import com.example.eyeqtest.MacularDegeneration.MacularDegenerationFront
import com.example.eyeqtest.Modals.ColorBlindTestModal
import com.example.eyeqtest.Modals.NewsletterModal
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {
    private var backNavigationLocked = false // Flag to lock back navigation
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var cbresult: NewsletterModal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val colorblindtest = view.findViewById<Button>(R.id.colorBlindTestBtn)
        val astigmatismtest = view.findViewById<Button>(R.id.astigmatismTestBtn)
        val masculartest = view.findViewById<Button>(R.id.mascularTestBtn)
        val contrastsentest = view.findViewById<Button>(R.id.contrastTestBtn)
        val uniqueFeatureBtn = view.findViewById<Button>(R.id.uniqueFeatureBtn)




        colorblindtest.setOnClickListener {
            val intent = Intent(activity, ColorBlindHome::class.java)
            startActivity(intent)
        }

        astigmatismtest.setOnClickListener {
            val intent = Intent(activity, AstigmatismFront::class.java)
            startActivity(intent)
        }

        masculartest.setOnClickListener {
            val intent = Intent(activity, MacularDegenerationFront::class.java)
            startActivity(intent)
        }

        contrastsentest.setOnClickListener {
            val intent = Intent(activity, ContrastSensivityFront::class.java)
            startActivity(intent)
        }

        uniqueFeatureBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView3, EyewarmupFragmnet())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    fun lockBackNavigation(locked: Boolean) {
        backNavigationLocked = locked
    }

    // Override the onBackPressed method to handle back navigation
    fun onBackPressed(): Boolean {
        return backNavigationLocked
    }


}




