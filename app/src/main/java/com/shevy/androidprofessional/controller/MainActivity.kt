package com.shevy.androidprofessional.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.shevy.androidprofessional.R
import com.shevy.androidprofessional.model.Question

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var lastButton: ImageButton
    private lateinit var questionTextView: TextView

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
        lastButton = findViewById(R.id.last_btn)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        lastButton.setOnClickListener {
            if (currentIndex != 0) {
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQuestion()
            }
        }

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        val questionTextResId = getString(questionBank[currentIndex].textResId)
        questionTextView.text = questionTextResId

    }

    private fun updateQuestion() {
        val questionTextResId = getString(questionBank[currentIndex].textResId)
        questionTextView.text = questionTextResId
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            getString(R.string.correct_toast)
        } else {
            getString(R.string.incorrect_toast)
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}