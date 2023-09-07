package com.example.eyeqtest.ColorBlindTest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.eyeqtest.ContrastSensivity
import com.example.eyeqtest.R

class ColorBlindTestRight : AppCompatActivity() {

    private var leftCorrectAnswers = 0
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private val questions = listOf(
        ContrastSensivity(R.drawable.cbtestplate1, listOf("74", "8", "69", "36"), 0),
        ContrastSensivity(R.drawable.cbtestplate2, listOf("8", "22", "73", "10"), 2),
        ContrastSensivity(R.drawable.cbtestplate3, listOf("25", "46", "11", "7"), 3),
        ContrastSensivity(R.drawable.cbtestplate4, listOf("18", "42", "78", "96"), 1),
        ContrastSensivity(R.drawable.cbtestplate5, listOf("26", "88", "54", "33"), 0),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_test_right)

        leftCorrectAnswers = intent.getIntExtra("correctAnswers", 0)

        val questionImageView = findViewById<ImageView>(R.id.cbTestImageRight)
        val answerButtons = listOf(
            findViewById<Button>(R.id.answer1btnR),
            findViewById<Button>(R.id.answer2btnR),
            findViewById<Button>(R.id.answer3btnR),
            findViewById<Button>(R.id.answer4btnR)
        )



        displayQuestion()


        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                checkAnswer(index)
            }
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            val questionImageView = findViewById<ImageView>(R.id.cbTestImageRight)
            questionImageView.setImageResource(currentQuestion.imageResourceId)


            val answerButtons = listOf(
                findViewById<Button>(R.id.answer1btnR),
                findViewById<Button>(R.id.answer2btnR),
                findViewById<Button>(R.id.answer3btnR),
                findViewById<Button>(R.id.answer4btnR)
            )

            currentQuestion.answers.forEachIndexed { index, answer ->
                answerButtons[index].text = answer
            }
        } else {

        }
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]

            if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {

                correctAnswers++

            } else {


            }


            currentQuestionIndex++
            displayQuestion()
        }


        if (currentQuestionIndex == questions.size) {
            val intent = Intent(this, ColorTestResults::class.java)
            intent.putExtra("rightcorrectAnswers", correctAnswers)
            intent.putExtra("leftcorrectAnswers", leftCorrectAnswers)
            startActivity(intent)
        }
    }
}