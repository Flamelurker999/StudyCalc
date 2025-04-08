package ru.learn.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var operation: String? = null
    private var firstNumber: Int? = null
    private var secondNumber: Int? = null
    private var result: Int? = null

  private companion object {
        const val DISPLAY_KEY = "display"
        const val OPERATION_KEY = "operation"
        const val FIRST_NUMBER_KEY = "firstNumber"
        const val SECOND_NUMBER_KEY = "secondNumber"
        const val RESULT_KEY = "result"
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.TextView)
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
            if (result != null) {
                result = null
                display.text = ""
            }
        }


        digitOne.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "1"
        }

        digitTwo.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "2"
        }

        digitThree.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "3"
        }

        digitFour.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "4"
        }

        digitFive.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "5"
        }

        digitSix.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "6"
        }

        digitSeven.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "7"
        }

        digitEight.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "8"
        }

        digitNine.setOnClickListener {
            checkResultForClear()
            display.text = display.text.toString() + "9"
        }

        digitZero.setOnClickListener {
            checkResultForClear()
            if (display.text.isNotEmpty() && result == null) {
                display.text = display.text.toString() + "0"
            }
        }


        plusButton.setOnClickListener {
            checkResultForClear()
            if (firstNumber == null && display.text.isNotEmpty()) {
                firstNumber = display.text.toString().toInt()
                operation = "+"
                display.text = firstNumber.toString() + " + "
            }
        }

        minusButton.setOnClickListener {
            checkResultForClear()
            if (firstNumber == null && display.text.isNotEmpty()) {
                firstNumber = display.text.toString().toInt()
                operation = "-"
                display.text = firstNumber.toString() + " - "
            }
        }

        multiplyButton.setOnClickListener {
            checkResultForClear()
            if (firstNumber == null && display.text.isNotEmpty()) {
                firstNumber = display.text.toString().toInt()
                operation = "*"
                display.text = firstNumber.toString() + " * "
            }
        }

        divisionButton.setOnClickListener {
            checkResultForClear()
            if (firstNumber == null && display.text.isNotEmpty()) {
                firstNumber = display.text.toString().toInt()
                operation = "/"
                display.text = firstNumber.toString() + " / "
            }
        }

        equalButton.setOnClickListener {
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



        clearButton.setOnClickListener {
            display.text = ""
            firstNumber = null
            secondNumber = null
            operation = null
        }


        commaButton.setOnClickListener {
            showToast("Hello, friend!")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(DISPLAY_KEY, display.text.toString())
        outState.putString(OPERATION_KEY, operation)
        outState.putString(RESULT_KEY, result.toString())
        outState.putString(FIRST_NUMBER_KEY, firstNumber.toString())
        outState.putString(SECOND_NUMBER_KEY, secondNumber.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val savedResult = savedInstanceState.getString(RESULT_KEY)
        val savedFirstNumber = savedInstanceState.getString(FIRST_NUMBER_KEY)
        val savedSecondNumber = savedInstanceState.getString(SECOND_NUMBER_KEY)

        display.text = savedInstanceState.getString(DISPLAY_KEY)
        operation = savedInstanceState.getString(OPERATION_KEY)

        result = if (savedResult != "null") {
            savedResult?.toInt()
        } else null

        firstNumber = if (savedFirstNumber != "null") {
            savedFirstNumber?.toInt()
        } else null

        secondNumber = if (savedSecondNumber != "null") {
            savedSecondNumber?.toInt()
        } else null

        super.onRestoreInstanceState(savedInstanceState)
    }


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }




}



