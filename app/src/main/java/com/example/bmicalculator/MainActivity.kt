package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var unitRadioGroup: RadioGroup
    private lateinit var resetButton: Button
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        unitRadioGroup = findViewById(R.id.unitRadioGroup)
        resetButton = findViewById(R.id.resetButton)
        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)


        // Reset button logic
        resetButton.setOnClickListener {
            weightEditText.text.clear()
            heightEditText.text.clear()
            resultTextView.text = ""
            // Reset to default unit selection
            unitRadioGroup.check(R.id.kgCmRadioButton)
        }

        // Calculate button logic (add unit conversion)
        calculateButton.setOnClickListener {
            val weightStr = weightEditText.text.toString()
            val heightStr = heightEditText.text.toString()
            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                var weight = weightStr.toFloat()
                var height = heightStr.toFloat()
                if (unitRadioGroup.checkedRadioButtonId == R.id.lbsInchesRadioButton) {
                    weight *= 0.453592f // Convert lbs to kg
                    height *= 2.54f     // Convert inches to cm

                }
                height /= 100 // Convert cm to meters
                val bmi = weight / (height * height)
                resultTextView.text = String.format("BMI: %.2f", bmi)
            } else {
                resultTextView.text = "Please enter both weight and height."
            }
        }
    }
}
