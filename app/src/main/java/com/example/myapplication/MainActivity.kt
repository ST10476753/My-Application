package com.example.myapplication

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

        private lateinit var GenerateMeal: Button
        private lateinit var timeTextView: TextView
        private lateinit var SuggestedMealTextView: TextView
        private lateinit var ClearBtn: Button
        private lateinit var ExitBtn: Button



        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContentView(R.layout.activity_main)


                GenerateMeal = findViewById(R.id.GenerateMealButton)
                timeTextView = findViewById(R.id.inputtimeTextView)
                SuggestedMealTextView = findViewById(R.id.SuggestedMealTextView)
                ClearBtn= findViewById(R.id.ClearBtn)
                ExitBtn = findViewById(R.id.ExitBtn)

                GenerateMeal.setOnClickListener {
                        showTimePickerDialog()
                }
                ClearBtn.setOnClickListener {
                        clearOutput()
                }

                ExitBtn.setOnClickListener {
                        finish() // This closes the current activity, effectively exiting the app
                }
        }

        private fun showTimePickerDialog() {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val timePickerDialog = TimePickerDialog(
                        this,
                        { _, selectedHour, selectedMinute ->
                                val selectedTime = Calendar.getInstance().apply {
                                        set(Calendar.HOUR_OF_DAY, selectedHour)
                                        set(Calendar.MINUTE, selectedMinute)
                                }
                                updateTimeAndMealSuggestion(selectedTime)
                        },
                        hour,
                        minute,
                        false // Use 12-hour format
                )
                timePickerDialog.show()
        }

        private fun updateTimeAndMealSuggestion(calendar: Calendar) {


                val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
                val mealSuggestion = when (hourOfDay) {
                        in 6..9 -> "Breakfast: Porridge, Muesli, Toasted Bacon and Egg" // Breakfast
                        in 10..11 -> "Morning Snack: Fruit Salad, Yogurt, Energy Bar" // Morning Snack
                        in 12..14 -> "Lunch: Pizza, Salad, Lasagna" // Lunch
                        in 15..17 -> "Afternoon Snack: Crackers, Cheese, Biltong" // Afternoon Snack
                        in 18..20 -> "Dinner: Chicken Alfredo Pasta, Salmon and Rice, Steak and Chips" // Dinner
                        in 21..23 -> "Evening Snack: Popcorn, Tea, Dried Fruit" // Evening Snack
                        else -> "Late Night Snack: Hard-Boiled Egg" // Late Night Snack
                }
                SuggestedMealTextView.text = "Meal Suggestion: $mealSuggestion"
        }
        private fun clearOutput() {
                timeTextView.text = "Selected Time: "
                SuggestedMealTextView.text = "Meal Suggestion: "
        }
}











