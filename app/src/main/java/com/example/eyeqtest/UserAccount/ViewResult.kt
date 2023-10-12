package com.example.eyeqtest.UserAccount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyeqtest.R

class ViewResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_result)

        val userId = intent.getStringExtra("id")

        val colorT = findViewById<button>(R.id.color_test)
        val asigT = findViewById<button>(R.id.asig_test)
        val contrstT = findViewById<button>(R.id.contrast_test)
        val macularT = findViewById<button>(R.id.macular_test)


    }
}