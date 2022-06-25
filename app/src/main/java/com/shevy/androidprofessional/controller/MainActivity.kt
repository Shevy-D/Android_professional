package com.shevy.androidprofessional.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.shevy.androidprofessional.R
import com.shevy.androidprofessional.model.Question

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private var isAnswered = false
    private var count = 1.0
    private var trueAnswer = 0.0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_america, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_btn)
        falseButton = findViewById(R.id.false_btn)
        nextButton = findViewById(R.id.next_btn)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            if (!isAnswered) {
                checkAnswer(true)
                isAnswered = true
            } else {
                alreadyAnswer()
            }
        }

        falseButton.setOnClickListener {
            if (!isAnswered) {
                checkAnswer(false)
                isAnswered = true
            } else {
                alreadyAnswer()
            }
        }

        nextButton.setOnClickListener {
            isAnswered = false
            currentIndex = (currentIndex + 1) % questionBank.size
            if (count >= 6.0) {
                val result = (trueAnswer/count*100).toInt()
                Toast.makeText(this, "You completed test. You result is $result%", Toast.LENGTH_LONG).show()
            } else {
                count++
                updateQuestion()
            }
        }

/*        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }*/

        val questionTextResId = getString(questionBank[currentIndex].textResId)
        questionTextView.text = questionTextResId

    }

    private fun alreadyAnswer() {
        Toast.makeText(this, "You already answered", Toast.LENGTH_SHORT).show()
    }

    private fun updateQuestion() {
        val questionTextResId = getString(questionBank[currentIndex].textResId)
        questionTextView.text = questionTextResId
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        var messageResId = ""
        if (userAnswer == correctAnswer) {
            messageResId = getString(R.string.correct_toast)
            trueAnswer++
        } else {
            messageResId = getString(R.string.incorrect_toast)
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}