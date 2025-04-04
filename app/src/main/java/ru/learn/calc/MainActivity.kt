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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //TODO Давай вынесем setContentView в отдельную функцию,
        // которая будет принимать на вход enum с нужной вёрсткой.
        setContentView(R.layout.activity_main_constrait)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var firstNumber: Int? = null
        var secondNumber: Int?
        var result: Int? = null
        var operation: String? = null

        val display: TextView = findViewById(R.id.TextView)

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

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}