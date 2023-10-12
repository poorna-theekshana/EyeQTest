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
import com.example.eyeqtest.Astigmatism.AstigmatismFront
import com.example.eyeqtest.ColorBlindTest.ColorBlindHome
import com.example.eyeqtest.ContrastSensivity.ContrastSensivityActivity
import com.example.eyeqtest.ContrastSensivity.ContrastSensivityFront
import com.example.eyeqtest.HandEyeCoordination.HandEyeCoordinationHome
import com.example.eyeqtest.MacularDegeneration.MacularDegenerationFront
import com.example.eyeqtest.blinkingrouting.EyesActivity

class HomeFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val colorblindtest = view.findViewById<Button>(R.id.colorBlindTestBtn)
        val astigmatismtest = view.findViewById<Button>(R.id.astigmatismTestBtn)
        val masculartest = view.findViewById<Button>(R.id.mascularTestBtn)
        val contrastsentest = view.findViewById<Button>(R.id.contrastTestBtn)
        val uniqueFeatureBtn = view.findViewById<Button>(R.id.uniqueFeatureBtn)
        val hand = view.findViewById<TextView>(R.id.textView2)

        hand.setOnClickListener{
            val intent = Intent(activity,HandEyeCoordinationHome::class.java)
            startActivity(intent)
        }

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
            val intent = Intent(activity, EyesActivity::class.java)
            startActivity(intent)
        }
        
        return view
    }


}