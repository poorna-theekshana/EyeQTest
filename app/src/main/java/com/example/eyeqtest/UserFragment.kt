package com.example.eyeqtest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.eyeqtest.ColorBlindTest.ColorBlindHome


class UserFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val colorblindtest = view.findViewById<Button>(R.id.button)

        colorblindtest.setOnClickListener {
            val intent = Intent(activity, ColorBlindHome::class.java)
            startActivity(intent)
        }



        return view
    }

}