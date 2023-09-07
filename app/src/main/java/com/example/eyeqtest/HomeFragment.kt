package com.example.eyeqtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.ColorBlindHome
import com.example.eyeqtest.MacularDegeneration.MacularDegenerationFront

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
            val intent = Intent(activity, ContrastSensivityActivity::class.java)
            startActivity(intent)
        }







        return view
    }


}