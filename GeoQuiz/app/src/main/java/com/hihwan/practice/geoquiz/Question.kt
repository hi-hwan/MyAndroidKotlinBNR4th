package com.hihwan.practice.geoquiz

import androidx.annotation.StringRes

data class Question(
    @StringRes val textResId: Int,
    val answer: Boolean,
    var isAnswered: Boolean = false,
    var userAnswer: Boolean = false)