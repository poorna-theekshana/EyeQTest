package com.example.eyeqtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class ContrastSensivityActivity : Activity() {

    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private val questions = listOf(
        ContrastSensivity(R.drawable.contrast_question1_image, listOf("L", "K", "N", "M"), 2),
        ContrastSensivity(R.drawable.contrast_question2_image, listOf("D", "R", "K", "S"), 3),
        ContrastSensivity(R.drawable.contrast_question3_image, listOf("K", "Y", "B", "L"), 0),
        ContrastSensivity(R.drawable.contrast_question4_image, listOf("P", "J", "L", "O"), 1),
        ContrastSensivity(R.drawable.contrast_question5_image, listOf("O", "C", "Q", "G"), 2),
        ContrastSensivity(R.drawable.contrast_question6_image, listOf("I", "J", "Y", "T"), 3),
        ContrastSensivity(R.drawable.contrast_question7_image, listOf("R", "D", "B", "P"), 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrast_sensivity)

        val questionImageView = findViewById<ImageView>(R.id.questionImageView)
        val answerButtons = listOf(
            findViewById<Button>(R.id.answer1Button),
            findViewById<Button>(R.id.answer2Button),
            findViewById<Button>(R.id.answer3Button),
            findViewById<Button>(R.id.answer4Button)
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
            val questionImageView = findViewById<ImageView>(R.id.questionImageView)
            questionImageView.setImageResource(currentQuestion.imageResourceId)


            val answerButtons = listOf(
                findViewById<Button>(R.id.answer1Button),
                findViewById<Button>(R.id.answer2Button),
                findViewById<Button>(R.id.answer3Button),
                findViewById<Button>(R.id.answer4Button)
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
                showToast("Correct!")
            } else {

                showToast("Incorrect!")
            }


            currentQuestionIndex++
            displayQuestion()
        }


        if (currentQuestionIndex == questions.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("correctAnswers", correctAnswers)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
