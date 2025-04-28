package ru.learn.calc

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var displayText = ""
    var operation: String? = null
    var firstNumber: Int? = null
    var secondNumber: Int? = null
    var result: Int? = null

    fun checkResultForClear(): String {
        if (result != null) {
            result = null
            displayText = ""
        }
        return displayText
    }

    fun clearValues(): String {
        operation = null
        firstNumber = null
        secondNumber = null
        result = null
        displayText = ""
        return displayText
    }

    fun setNumbersToNull(resultNumber: Int? = 0) {
        firstNumber = null
        secondNumber = null
        if (resultNumber == null) result = 0
    }

    fun equal(
        textInput: String,
        plusSymbol: String,
        minusSymbol: String,
        multiplySymbol: String,
        divisionSymbol: String,
        zeroText: String
    ): String {
        firstNumber?.let { first ->
            try {
                val list: List<String> = textInput.split(' ')
                if (list.last() != "") {
                    secondNumber = list.last().toInt()
                    secondNumber?.let { second ->
                        result = when (operation) {
                            plusSymbol -> first + second
                            minusSymbol -> first - second
                            multiplySymbol -> first * second
                            divisionSymbol -> first / second
                            else -> null
                        }
                        setNumbersToNull()
                        displayText = result.toString()
                    }
                }
            } catch (e: ArithmeticException) {
                setNumbersToNull(result)
                displayText = zeroText
            }
        }
        return displayText
    }

    fun printLiteral(
        literal: String?,
        firstNumber: Int? = null,
        textInput: String
    ): String {
        displayText = if (firstNumber == null) {
            "$textInput$literal"
        } else {
            "$firstNumber $literal "
        }
        return displayText
    }
}