package com.example.smartbartender

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ExtraIngredientsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.extraingredients_fragment)
        val extraIngredients = intent.getSerializableExtra("extraIngredients") as? MutableMap<String, Int>
        val textView = findViewById<TextView>(R.id.extraIngredientsTextView)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val extraIngredientsText = StringBuilder()
        extraIngredientsText.append("Extra Ingredients:\n")
        if(extraIngredients != null){
            // Iterate through the map and concatenate the values
            for ((ingredientName, ingredientValue) in extraIngredients) {
                extraIngredientsText.append("$ingredientName\n")
            }
        } else{
            extraIngredientsText.append("No extra ingredients")
        }

        textView.text = extraIngredientsText.toString()
        doneButton.setOnClickListener {
            val intent = Intent(this, FinishedActivity::class.java)
            startActivity(intent)
        }
    }
}