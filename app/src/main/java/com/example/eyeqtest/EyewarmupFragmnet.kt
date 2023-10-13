package com.example.eyeqtest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.eyeqtest.ContrastSensivity.ContrastSensivityFront
import com.example.eyeqtest.HandEyeCoordination.HandEyeCoordinationHome
import com.example.eyeqtest.blinkingrouting.BlinkingFront
import com.example.eyeqtest.blinkingrouting.EyesActivity
import com.example.eyeqtest.focusshift.HiddenWordActivity

class EyewarmupFragmnet : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_eyewarmup, container, false)

        val eyeblinking = view.findViewById<Button>(R.id.eyeblinking)
        val Focusshifts = view.findViewById<Button>(R.id.Focusshifts)
        val handeye = view.findViewById<Button>(R.id.handeyecoordiation)

        eyeblinking.setOnClickListener {
             val intent = Intent(activity, BlinkingFront::class.java)
             startActivity(intent)
        }

        Focusshifts.setOnClickListener {
            val intent = Intent(activity, HiddenWordActivity::class.java)
            startActivity(intent)
        }

        handeye.setOnClickListener{
            val intent = Intent(activity,HandEyeCoordinationHome::class.java)
            startActivity(intent)
        }


        return view
    }

}
