package ru.learn.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private fun chooseLayout(layout: ActivityMainLayouts) {
        setContentView(
            when (layout) {
                ActivityMainLayouts.LINEAR -> R.layout.activity_main_linear
                ActivityMainLayouts.CONSTRAINT -> R.layout.activity_main_constrait
                ActivityMainLayouts.FRAME -> R.layout.activity_main_frame
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val layout = ActivityMainLayouts.CONSTRAINT
        chooseLayout(layout)

        val calcValues = ViewModelProvider(this)[CalcViewModel::class.java]
        val display = findViewById<TextView>(R.id.TextView)
        display.text = calcValues.displayText

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val clearButton: Button = findViewById(R.id.clearButton)
        val plusButton: Button = findViewById(R.id.plusButton)
        val minusButton: Button = findViewById(R.id.minusButton)
        val multiplyButton: Button = findViewById(R.id.multButton)
        val divisionButton: Button = findViewById(R.id.divButton)
        val commaButton: Button = findViewById(R.id.commaButton)
        val equalButton: Button = findViewById(R.id.equalButton)

        val digitOne: Button = findViewById(R.id.digitOneButton)
        val digitTwo: Button = findViewById(R.id.digitTwoButton)
        val digitThree: Button = findViewById(R.id.digitThreeButton)
        val digitFour: Button = findViewById(R.id.digitFourButton)
        val digitFive: Button = findViewById(R.id.digitFiveButton)
        val digitSix: Button = findViewById(R.id.digitSixButton)
        val digitSeven: Button = findViewById(R.id.digitSevenButton)
        val digitEight: Button = findViewById(R.id.digitEightButton)
        val digitNine: Button = findViewById(R.id.digitNineButton)
        val digitZero: Button = findViewById(R.id.digitZeroButton)


        fun checkResultForClear() {
            with(calcValues) {
                if (result != null) {
                    result = null
                    displayText = ""
                    display.text = displayText
                }
            }
        }


        fun printLiteral(literal: String?, firstNumber: Int? = null) {
            if (firstNumber == null) {
                calcValues.displayText = "${display.text}$literal"
                display.text = calcValues.displayText

            } else if (firstNumber != null) {
                calcValues.displayText = "$firstNumber $literal "
                display.text = calcValues.displayText
            }
        }


        digitOne.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.one_text))
        }

        digitTwo.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.two_text))
        }

        digitThree.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.three_text))
        }

        digitFour.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.four_text))
        }

        digitFive.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.five_text))
        }

        digitSix.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.six_text))
        }

        digitSeven.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.seven_text))
        }

        digitEight.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.eight_text))
        }

        digitNine.setOnClickListener {
            checkResultForClear()
            printLiteral(getString(R.string.nine_text))
        }

        digitZero.setOnClickListener {
            checkResultForClear()
            if (display.text.isNotEmpty()) {
                printLiteral(getString(R.string.zero_text))
            }
        }


        plusButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = getString(R.string.plus_text)
                    printLiteral(operation, firstNumber)
                }
            }
        }

        minusButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = getString(R.string.minus_text)
                    printLiteral(operation, firstNumber)
                }
            }
        }

        multiplyButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = getString(R.string.multiply_text)
                    printLiteral(operation, firstNumber)
                }
            }
        }

        divisionButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = getString(R.string.division_text)
                    printLiteral(operation, firstNumber)
                }
            }
        }

        equalButton.setOnClickListener {
            with(calcValues) {
                firstNumber?.let { first ->
                    try {
                        val list: List<String> = display.text.toString().split(' ')
                        if (list.last() != "") {
                            secondNumber = list.last().toInt()
                            secondNumber?.let { second ->
                                result = when (operation) {
                                    getString(R.string.plus_text) -> first + second
                                    getString(R.string.minus_text) -> first - second
                                    getString(R.string.multiply_text) -> first * second
                                    getString(R.string.division_text) -> first / second
                                    else -> null
                                }
                                setNumbersToNull()
                                displayText = result.toString()
                                display.text = displayText
                            }
                        }
                    } catch (e: ArithmeticException) {
                        setNumbersToNull(result)
                        displayText = getString(R.string.division_by_zero_text)
                        display.text = displayText
                    }
                }
            }
        }


        clearButton.setOnClickListener {
            calcValues.clearValues()
            display.text = calcValues.displayText
        }


        commaButton.setOnClickListener {
            showToast("Hello, friend!")
        }

    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}



