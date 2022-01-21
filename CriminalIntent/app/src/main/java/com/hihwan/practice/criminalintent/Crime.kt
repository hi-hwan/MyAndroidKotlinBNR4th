package com.hihwan.practice.criminalintent

import android.text.format.DateFormat
import java.util.*

data class Crime(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: CharSequence = DateFormat.format("EEEE, MMM d, yyyy", Date()),
    var isSolved: Boolean = false,
    var requiresPolice: Boolean = false
)
