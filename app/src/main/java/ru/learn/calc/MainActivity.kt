package ru.learn.calc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val calcViewModel by viewModels<MainActivityViewModel>()

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

        val displayTextView = findViewById<TextView>(R.id.TextView)
        displayTextView.text = calcViewModel.displayText

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

        handleNumberButtons(displayTextView)
        handleOperatorButtons()

        plusButton.setOnClickListener {
            checkResultForClear(displayTextView)
            with(calcViewModel) {
                if (firstNumber == null && displayTextView.text.isNotEmpty()) {
                    firstNumber = displayTextView.text.toString().toInt()
                    operation = getString(R.string.plus_text)
                    printLiteral(operation, firstNumber, displayTextView)
                }
            }
        }

        minusButton.setOnClickListener {
            checkResultForClear(displayTextView)
            with(calcViewModel) {
                if (firstNumber == null && displayTextView.text.isNotEmpty()) {
                    firstNumber = displayTextView.text.toString().toInt()
                    operation = getString(R.string.minus_text)
                    printLiteral(operation, firstNumber, displayTextView)
                }
            }
        }

        multiplyButton.setOnClickListener {
            checkResultForClear(displayTextView)
            with(calcViewModel) {
                if (firstNumber == null && displayTextView.text.isNotEmpty()) {
                    firstNumber = displayTextView.text.toString().toInt()
                    operation = getString(R.string.multiply_text)
                    printLiteral(operation, firstNumber, displayTextView)
                }
            }
        }

        divisionButton.setOnClickListener {
            checkResultForClear(displayTextView)
            with(calcViewModel) {
                if (firstNumber == null && displayTextView.text.isNotEmpty()) {
                    firstNumber = displayTextView.text.toString().toInt()
                    operation = getString(R.string.division_text)
                    printLiteral(operation, firstNumber, displayTextView)
                }
            }
        }

        equalButton.setOnClickListener {
            with(calcViewModel) {
                firstNumber?.let { first ->
                    try {
                        val list: List<String> = displayTextView.text.toString().split(' ')
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
                                displayTextView.text = displayText
                            }
                        }
                    } catch (e: ArithmeticException) {
                        setNumbersToNull(result)
                        displayText = getString(R.string.division_by_zero_text)
                        displayTextView.text = displayText
                    }
                }
            }
        }


        clearButton.setOnClickListener {
            calcViewModel.clearValues()
            displayTextView.text = calcViewModel.displayText
        }


        commaButton.setOnClickListener {
            showToast("Hello, friend!")
        }

    }

    private fun handleOperatorButtons() {
    }

    private fun handleNumberButtons(displayTextView: TextView) {
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

        digitOne.handleButtonClick(displayTextView)
        digitTwo.handleButtonClick(displayTextView)
        digitThree.handleButtonClick(displayTextView)
        digitFour.handleButtonClick(displayTextView)
        digitFive.handleButtonClick(displayTextView)
        digitSix.handleButtonClick(displayTextView)
        digitSeven.handleButtonClick(displayTextView)
        digitEight.handleButtonClick(displayTextView)
        digitNine.handleButtonClick(displayTextView)
        digitZero.handleButtonClick(displayTextView)
    }

    private fun checkResultForClear(textView: TextView) {
        with(calcViewModel) {
            if (result != null) {
                result = null
                displayText = ""
                textView.text = displayText
            }
        }
    }

    private fun Button.handleButtonClick(displayTextView: TextView) {
        setOnClickListener {
            checkResultForClear(textView = displayTextView)
            printLiteral(literal = text.toString(), displayTextView = displayTextView)
        }
    }

    private fun printLiteral(
        literal: String?,
        firstNumber: Int? = null,
        displayTextView: TextView
    ) {
        if (firstNumber == null) {
            calcViewModel.displayText = "${displayTextView.text}$literal"
        } else {
            calcViewModel.displayText = "$firstNumber $literal "
        }
        displayTextView.text = calcViewModel.displayText
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}



