package ru.learn.calc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import ru.learn.calc.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentCalculatorBinding must not be null")
    private val calculatorViewModel by viewModels<CalculatorViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textView.text = calculatorViewModel.displayText

        handleNumberButtons()
        handleOperatorButtons()
        handleEqualButton()
        handleClearButton()
        handleCommaButton()

        return view
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
                    textView.text = calculatorViewModel.checkResultForClear()
                    textView.text = calculatorViewModel.printLiteral(
                        literal = text.toString(),
                        textInput = textView.text.toString()
                    )
                }
            }
        }

        private fun Button.handleOperatorButtonClick(operations: String) {
            setOnClickListener {
                with(calculatorViewModel) {
                    binding.textView.text = calculatorViewModel.checkResultForClear()
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
                    textView.text = calculatorViewModel.equal(
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
                binding.textView.text = calculatorViewModel.clearValues()
            }
        }

        private fun Button.handleCommaButtonClick() {
            setOnClickListener {
                showToast("Hello, friend!")
            }
        }

        fun showToast(text: String) {
            context?.let {
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
            }
        }

    }

