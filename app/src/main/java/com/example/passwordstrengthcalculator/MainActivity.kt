package com.example.passwordstrengthcalculator

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var color: Int = R.color.weak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val passwordStrengthCalculator = PasswordStrengthCalculator()
        password_input.addTextChangedListener(passwordStrengthCalculator)
        
        // Observers
        passwordStrengthCalculator.strengthLevel.observe(this, Observer { strengthLevel ->
            displayStrengthLevel(strengthLevel)
        })

        passwordStrengthCalculator.strengthColor.observe(this, Observer { strengthColor ->
            color = strengthColor
        })

        passwordStrengthCalculator.lowerCase.observe(this, Observer { value ->
            displayPasswordSuggestions(value, lowerCase__img, lowerCase__txt)
        })

        passwordStrengthCalculator.upperCase.observe(this, Observer { value ->
            displayPasswordSuggestions(value, lowerCase__img, lowerCase__txt)
        })

        passwordStrengthCalculator.digit.observe(this, Observer {  value ->
            displayPasswordSuggestions(value, lowerCase__img, lowerCase__txt)
        })

        passwordStrengthCalculator.specialChar.observe(this, Observer { value ->
            displayPasswordSuggestions(value, lowerCase__img, lowerCase__txt)
        })
    }

    private fun displayPasswordSuggestions(value: Int, imageView: ImageView, textView: TextView) {
        if (value == 1){
            imageView.setColorFilter(ContextCompat.getColor(this, R.color.bulletproof))
            textView.setTextColor(ContextCompat.getColor(this, R.color.bulletproof))
        }else{
            imageView.setColorFilter(ContextCompat.getColor(this, R.color.darkGray))
            textView.setTextColor(ContextCompat.getColor(this, R.color.darkGray))
        }

    }

    private fun displayStrengthLevel(strengthLevel: String) {
        button.isEnabled = strengthLevel.contains("BULLETPROOF")

        strenth_level_text.text = strengthLevel
        strenth_level_text.setTextColor(ContextCompat.getColor(this, color))
        strength_level_indicator.setBackgroundColor(ContextCompat.getColor(this, color))

    }
}