package ru.learn.calc

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.learn.calc.databinding.ActivityMainConstraitBinding

class MainActivity : AppCompatActivity() {

    private val calcViewModel by viewModels<MainActivityViewModel>()
    private var _binding: ActivityMainConstraitBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainConstraitBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityMainConstraitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = calcViewModel.displayText

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        handleNumberButtons()
        handleOperatorButtons()
        handleEqualButton()
        handleClearButton()
        handleCommaButton()
    }

    private fun handleOperatorButtons() {
        with(binding) {
            plusButton.handleOperatorButtonClick(getString(R.string.plus_text))
            minusButton.handleOperatorButtonClick(getString(R.string.minus_text))
            multButton.handleOperatorButtonClick(getString(R.string.multiply_text))
            divButton.handleOperatorButtonClick(getString(R.string.division_text))
        }
    }

    private fun handleEqualButton() {
        binding.equalButton.handleEqualButtonClick()
    }

    private fun handleClearButton() {
        binding.clearButton.handleClearButtonClick()
    }

    private fun handleCommaButton() {
        binding.commaButton.handleCommaButtonClick()
    }

    private fun handleNumberButtons() {
        with(binding) {
            digitOneButton.handleNumberButtonClick()
            digitTwoButton.handleNumberButtonClick()
            digitThreeButton.handleNumberButtonClick()
            digitFourButton.handleNumberButtonClick()
            digitFiveButton.handleNumberButtonClick()
            digitSixButton.handleNumberButtonClick()
            digitSevenButton.handleNumberButtonClick()
            digitEightButton.handleNumberButtonClick()
            digitNineButton.handleNumberButtonClick()
            digitZeroButton.handleNumberButtonClick()
        }
    }

    private fun Button.handleNumberButtonClick() {
        setOnClickListener {
            with(binding) {
                textView.text = calcViewModel.checkResultForClear()
                textView.text = calcViewModel.printLiteral(
                    literal = text.toString(),
                    textInput = textView.text.toString()
                )
            }
        }
    }

    private fun Button.handleOperatorButtonClick(operations: String) {
        setOnClickListener {
            with(calcViewModel) {
                binding.textView.text = calcViewModel.checkResultForClear()
                if (firstNumber == null && binding.textView.text.isNotEmpty()) {
                    firstNumber = binding.textView.text.toString().toInt()
                    operation = operations
                    binding.textView.text = printLiteral(
                        operations,
                        firstNumber,
                        binding.textView.text.toString()
                    )
                }
            }
        }
    }

    private fun Button.handleEqualButtonClick() {
        setOnClickListener {
            with(binding) {
                textView.text = calcViewModel.equal(
                    textView.text.toString(),
                    getString(R.string.plus_text),
                    getString(R.string.minus_text),
                    getString(R.string.multiply_text),
                    getString(R.string.division_text),
                    getString(R.string.division_by_zero_text)
                )
            }
        }
    }

    private fun Button.handleClearButtonClick() {
        setOnClickListener {
            binding.textView.text = calcViewModel.clearValues()
        }
    }

    private fun Button.handleCommaButtonClick() {
        setOnClickListener {
            showToast("Hello, friend!")
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}