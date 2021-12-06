package com.hihwan.practice.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    val quizSize: Int
        get() = questionBank.size

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    var currentQuestionIsAnswered: Boolean
        get() = questionBank[currentIndex].isAnswered
        set(value) {questionBank[currentIndex].isAnswered = value}

    var currentQuestionUserAnswer: Boolean
        get() = questionBank[currentIndex].userAnswer
        set(value) {questionBank[currentIndex].userAnswer = value}

    val isLastAnswer: Boolean
        get() = (currentIndex == questionBank.size - 1)

    fun getTotalScore(): Int {
        var score = 0
        for (q in questionBank) {
            if (q.isAnswered && q.userAnswer == q.answer) {
                score++
            }
        }
        return score
    }
    fun getPercentageCorrectAnswers(): Float {
        return getTotalScore().toFloat() / questionBank.size * 100
    }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex = if (currentIndex > 0) {
            currentIndex - 1
        } else {
            questionBank.size - 1
        }
    }
}