package ru.learn.calc

import java.io.Serializable

data class CalcValues(
    var operation: String? = null,
    var firstNumber: Int? = null,
    var secondNumber: Int? = null,
    var result: Int? = null,
    var displayText : String = ""
) : Serializable