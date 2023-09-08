package com.example.eyeqtest.ColorBlindTest

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.eyeqtest.ContrastSensivity.ContrastSensivity
import com.example.eyeqtest.R

class ColorBlindTestLeft : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private val questions = listOf(
        ContrastSensivity(R.drawable.cbtestplate6, listOf("5", "70", "3", "96"), 0),
        ContrastSensivity(R.drawable.cbtestplate7, listOf("44", "63", "14", "8"), 3),
        ContrastSensivity(R.drawable.cbtestplate8, listOf("65", "12", "54", "2"), 1),
        ContrastSensivity(R.drawable.cbtestplate9, listOf("84", "3", "78", "30"), 1),
        ContrastSensivity(R.drawable.cbtestplate10, listOf("88", "49", "29", "20"), 2),
    )
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_blind_test_left)


        val questionImageView = findViewById<ImageView>(R.id.cbTestImageLeft)
        val answerButtons = listOf(
            findViewById<Button>(R.id.answer1btnL),
            findViewById<Button>(R.id.answer2btnL),
            findViewById<Button>(R.id.answer3btnL),
            findViewById<Button>(R.id.answer4btnL)
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
            val questionImageView = findViewById<ImageView>(R.id.cbTestImageLeft)
            questionImageView.setImageResource(currentQuestion.imageResourceId)


            val answerButtons = listOf(
                findViewById<Button>(R.id.answer1btnL),
                findViewById<Button>(R.id.answer2btnL),
                findViewById<Button>(R.id.answer3btnL),
                findViewById<Button>(R.id.answer4btnL)
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
            val intent = Intent(this, CloseLeftEye::class.java)
            intent.putExtra("correctAnswers", correctAnswers)
            startActivity(intent)
        }
    }

}