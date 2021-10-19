package com.hihwan.practice.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG: String = "GeoQuiz"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { view: View ->
            currentIndex += 1
            if (currentIndex == questionBank.size) {
                currentIndex = 0;
                checkPercentOfCorrectAnswer()
            }
            updateQuestion()
        }

        prevButton.setOnClickListener { view: View ->
            currentIndex -= 1
            if (currentIndex < 0) {
                currentIndex = 0;
                Toast.makeText(this, R.string.warn_first_question, Toast.LENGTH_LONG).show()
            }
            updateQuestion()
        }

        questionTextView.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        val isAnswered = questionBank[currentIndex].isAnswered
        if (isAnswered) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        questionBank[currentIndex].isAnswered = true
        questionBank[currentIndex].userAnswer = userAnswer
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (correctAnswer == userAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun checkPercentOfCorrectAnswer() {
        var correctAnswer = 0
        for (q in questionBank) {
            if (q.isAnswered) {
                if (q.answer == q.userAnswer) {
                    correctAnswer++
                }
            }
        }
        val percent = (correctAnswer.toFloat() / questionBank.size.toFloat()) * 100
        val info = String.format(
            resources.getString(R.string.info_correct_answers),
            questionBank.size, // Total
            correctAnswer, // Count of correct answer
            percent // Percent of correct answer
        )
        Toast.makeText(this, info, Toast.LENGTH_LONG).show()
    }
}