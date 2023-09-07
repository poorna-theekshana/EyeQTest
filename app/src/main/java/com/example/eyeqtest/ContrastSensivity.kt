package com.example.eyeqtest

data class ContrastSensivity(
    val imageResourceId: Int,
    val answers: List<String>,
    val correctAnswerIndex: Int
)
