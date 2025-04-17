package ru.learn.calc

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var displayText = ""
    var operation: String? = null
    var firstNumber: Int? = null
    var secondNumber: Int? = null
    var result: Int? = null


    fun clearValues() {
        displayText = ""
        operation = null
        firstNumber = null
        secondNumber = null
        result = null
    }

    fun setNumbersToNull(resultNumber: Int? = 0) {
            firstNumber = null
            secondNumber = null
            if (resultNumber == null) result = 0
    }

}