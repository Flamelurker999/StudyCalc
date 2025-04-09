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

    private lateinit var calcValues: CalcValues
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

        calcValues = CalcValues()
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

        fun printLiteral(literal: String?, firstNumber: Int? = null) {
                if (firstNumber == null) {
                    display.text = display.text.toString() + literal
                } else display.text = firstNumber.toString() + " $literal "
        }


        digitOne.setOnClickListener {
            checkResultForClear()
            printLiteral("1")
        }

        digitTwo.setOnClickListener {
            checkResultForClear()
            printLiteral("2")
        }

        digitThree.setOnClickListener {
            checkResultForClear()
            printLiteral("3")
        }

        digitFour.setOnClickListener {
            checkResultForClear()
            printLiteral("4")
        }

        digitFive.setOnClickListener {
            checkResultForClear()
            printLiteral("5")
        }

        digitSix.setOnClickListener {
            checkResultForClear()
            printLiteral("6")
        }

        digitSeven.setOnClickListener {
            checkResultForClear()
            printLiteral("7")
        }

        digitEight.setOnClickListener {
            checkResultForClear()
            printLiteral("8")
        }

        digitNine.setOnClickListener {
            checkResultForClear()
            printLiteral("9")
        }

        digitZero.setOnClickListener {
            checkResultForClear()
            if (display.text.isNotEmpty()) {
                printLiteral("0")
            }
        }


        plusButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = "+"
                    printLiteral(operation, firstNumber)
                }
            }
        }

        minusButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = "-"
                    printLiteral(operation, firstNumber)
                }
            }
        }

        multiplyButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = "*"
                    printLiteral(operation, firstNumber)
                }
            }
        }

        divisionButton.setOnClickListener {
            checkResultForClear()
            with(calcValues) {
                if (firstNumber == null && display.text.isNotEmpty()) {
                    firstNumber = display.text.toString().toInt()
                    operation = "/"
                    printLiteral(operation, firstNumber)
                }
            }
        }

        equalButton.setOnClickListener {
            with(calcValues) {
                if (firstNumber != null) {
                    try {
                        val list: List<String> = display.text.toString().split(' ')
                        if (list.last() != "") {
                            secondNumber = list.last().toInt()
                            result = when (operation) {
                                "+" -> firstNumber!! + secondNumber!!
                                "-" -> firstNumber!! - secondNumber!!
                                "*" -> firstNumber!! * secondNumber!!
                                "/" -> firstNumber!! / secondNumber!!
                                else -> null
                            }
                            firstNumber = null
                            secondNumber = null
                            display.text = result.toString()
                        }
                    } catch (e: ArithmeticException) {
                        firstNumber = null
                        secondNumber = null
                        result = 0
                        display.text = "Делить на 0 нельзя!"
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



