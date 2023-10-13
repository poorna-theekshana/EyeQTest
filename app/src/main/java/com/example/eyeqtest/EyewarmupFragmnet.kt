package com.example.eyeqtest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.eyeqtest.HandEyeCoordination.HandEyeCoordinationHome
import com.example.eyeqtest.blinkingrouting2.EyesActivity2
import com.example.eyeqtest.blinkingrouting3.EyesActivity3
import com.example.eyeqtest.focusshift2.HiddenWordActivity2

class EyewarmupFragmnet : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_eyewarmup, container, false)

        val eyeblinking = view.findViewById<Button>(R.id.eyeblinking)
        val Focusshifts = view.findViewById<Button>(R.id.Focusshifts)
        val eyeyoga = view.findViewById<Button>(R.id.eyeyoga)
        val handeye = view.findViewById<Button>(R.id.handeyecoordiation)

        eyeblinking.setOnClickListener {
            val intent = Intent(activity, EyesActivity3::class.java)
            startActivity(intent)
        }

        Focusshifts.setOnClickListener {
            val intent = Intent(activity, HiddenWordActivity2::class.java)
            startActivity(intent)
        }

        eyeyoga.setOnClickListener {
            val intent = Intent(activity, EyesActivity2::class.java)
            startActivity(intent)
        }

        handeye.setOnClickListener {
            val intent = Intent(activity, HandEyeCoordinationHome::class.java)
            startActivity(intent)
        }

        return view
    }
}
