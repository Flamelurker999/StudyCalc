package ru.learn.calc

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity() {

    private var isShowingHistory = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.main, CalculatorFragment())
            }
        }
        val moveToHistory = findViewById<FloatingActionButton>(R.id.switchFragmentButton)
        moveToHistory.setOnClickListener {
            if (!isShowingHistory) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.main, HistoryFragment())
                    addToBackStack(null)
                }
                isShowingHistory = true
            } else {
                supportFragmentManager.popBackStack()
                isShowingHistory = false
            }
        }
    }
}