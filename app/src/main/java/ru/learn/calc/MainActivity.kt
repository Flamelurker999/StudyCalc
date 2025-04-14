package ru.learn.calc

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private var calcValues = CalcValues()
    private lateinit var display: TextView

    private companion object {
        const val CALC_VALUE_KEY = "calc"
    }

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

        display = findViewById(R.id.TextView)

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
                    display.text = ""
                }
            }
        }

        fun setNumbersToNull(resultNumber: Int? = 0) {
            with(calcValues) {
                firstNumber = null
                secondNumber = null
                if (resultNumber == null) result = 0
            }
        }

        fun printLiteral(literal: String?, firstNumber: Int? = null) {
            if (firstNumber == null) {
                display.text = "${display.text}$literal"

            } else if (firstNumber != null) {
                display.text =
                    "$firstNumber $literal "
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
                                display.text = result.toString()
                            }
                        }
                    } catch (e: ArithmeticException) {
                        setNumbersToNull(result)
                        display.text = getString(R.string.division_by_zero_text)
                    }
                }
            }
        }


        clearButton.setOnClickListener {
            with(calcValues) {
                display.text = ""
                firstNumber = null
                secondNumber = null
                operation = null
            }
        }


        commaButton.setOnClickListener {
            showToast("Hello, friend!")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        calcValues.displayText = display.text.toString()
        outState.putSerializable(CALC_VALUE_KEY, calcValues)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        calcValues = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(CALC_VALUE_KEY, CalcValues::class.java) as CalcValues
        } else {
            savedInstanceState.getSerializable(CALC_VALUE_KEY) as CalcValues
        }
        display.text = calcValues.displayText
        super.onRestoreInstanceState(savedInstanceState)
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}



