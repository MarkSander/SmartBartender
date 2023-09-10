package com.example.smartbartender

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.smartbartender.GridViewModal

class DrinkDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_fragment)

        // Retrieve the drink information from the intent
        val drinkName = intent.getStringExtra("drinkName")
        val drinkImageResource = intent.getIntExtra("drinkImageResource", 0)
        val drinkIngredients = intent.getSerializableExtra("drinkIngredients") as? MutableMap<String, Int>



// Now you have the individual data points to populate your UI components


        // Initialize UI components
        val drinkImageView = findViewById<ImageView>(R.id.drinkImage)
        val drinkNameView = findViewById<TextView>(R.id.drinkName)

        val ingredientsText = StringBuilder()
        ingredientsText.append("Ingredients:\n")
        if(drinkIngredients != null){
            // Iterate through the map and concatenate the values
            for ((ingredientName, ingredientValue) in drinkIngredients) {
                ingredientsText.append("$ingredientName\n")
            }
        } else{
            ingredientsText.append("No Ingredients found")
        }

        // Populate UI components with drink information
        drinkImageView.setImageResource(drinkImageResource)
        drinkNameView.text = drinkName
        val textView = findViewById<TextView>(R.id.drinkIngredients)
        textView.text = ingredientsText.toString()

        // Add more code to populate other UI components with additional drink details
    }
}
