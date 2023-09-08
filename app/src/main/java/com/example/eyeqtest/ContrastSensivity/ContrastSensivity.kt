package com.example.eyeqtest.ContrastSensivity

data class ContrastSensivity(
    val imageResourceId: Int,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
