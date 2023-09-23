package com.example.smartbartender

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class DrinkCustomDetailActivity : AppCompatActivity() {
    private lateinit var drinksViewModel: DrinksViewModel
    // Access appPreferences in your activities or fragments
    private lateinit var appPreferences: AppPreferences
    private val rasberryHttpRequests = RasberryHttpRequests()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform)
            window.sharedElementEnterTransition = transition
        }
        setContentView(R.layout.detail_customdrink_fragment)
        appPreferences = (applicationContext as MyApplication).appPreferences
        drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)
        rasberryHttpRequests.sendDrinkDetailHttpRequestAsync()

        // Access ViewModel properties
// Retrieve the saved drinkInput values from SharedPreferences
        val pump1 = appPreferences.getDrinkInput("drinkInput1", "Empty")
        val pump2 = appPreferences.getDrinkInput("drinkInput2", "Empty")
        val pump3 = appPreferences.getDrinkInput("drinkInput3", "Empty")
        val pump4 = appPreferences.getDrinkInput("drinkInput4", "Empty")


        // Retrieve the drink information from the intent
        val drinkName = intent.getStringExtra("drinkName")
        val drinkImageResource = intent.getIntExtra("drinkImageResource", 0)
        val drinkIngredients = intent.getSerializableExtra("drinkIngredients") as? MutableMap<String, Int>
        val extraIngredients = intent.getSerializableExtra("extraIngredients") as? MutableMap<String, Int>

// Now you have the individual data points to populate your UI components

        // Initialize UI components
        val drinkImageView = findViewById<ImageView>(R.id.drinkImage)
        val drinkNameView = findViewById<TextView>(R.id.drinkName)
        val pourButton = findViewById<Button>(R.id.pourDrinkButton)
        pourButton.setOnClickListener {
            var pump1Value = 0
            var pump2Value = 0
            var pump3Value = 0
            var pump4Value = 0
            if(drinkIngredients != null){
                // Iterate through the map and concatenate the values
                for ((ingredientName, ingredientValue) in drinkIngredients) {
                    when (ingredientName) {
                        detailDrinkInput1 -> pump1Value = ingredientValue
                        detailDrinkInput2 -> pump2Value = ingredientValue
                        detailDrinkInput3 -> pump3Value = ingredientValue
                        detailDrinkInput4 -> pump4Value = ingredientValue
                        else -> {
                            Log.e("Match Error", "Ingredient $ingredientName did not match any pump value, pump2: $detailDrinkInput2")
                            // Handle the case where the ingredient doesn't match any pump
                        }
                    }
                }
            } else{
                Log.e("Ingredient error","No Ingredients found")
            }
            GlobalScope.launch(Dispatchers.IO) {

                Log.i("Info", "Request being send to pump api localhost/$pump1Value/$pump2Value/$pump3Value/$pump4Value")
                val result = rasberryHttpRequests.sendFillAndFlushHttpRequestAsync(pump1Value, pump2Value, pump3Value, pump4Value)

                // Check if the result contains "Finished"
                if (result == "Finished") {
                    //TODO Create function to switch to a new view containing the extra ingredients
                    if(extraIngredients != null){
                        if (extraIngredients.isNotEmpty()){
                            val intent = Intent(applicationContext, ExtraIngredientsActivity::class.java)
                            intent.putExtra("extraIngredients", HashMap(extraIngredients))
                            Log.d("Start Activity", "Starting a new ExtraIngredientsActivity")
                            startActivity(intent)
                        }
                    }
                } else {
                    Log.e("Sending Request", "No Response named Finished was found")
                }

            }
        }
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
        if(extraIngredients != null){
        val extraIngredientsText = StringBuilder()
        extraIngredientsText.append("Extra Ingredients:\n")

            // Iterate through the map and concatenate the values
            for ((ingredientName, ingredientValue) in extraIngredients) {
                extraIngredientsText.append("$ingredientName\n")
            }
        }

        val sharedImageView = findViewById<ImageView>(R.id.drinkImage)
        ViewCompat.setTransitionName(sharedImageView, "drinkImageTransition")

        // Populate UI components with drink information
        drinkImageView.setImageResource(drinkImageResource)
        drinkNameView.text = drinkName
        val textView = findViewById<TextView>(R.id.drinkIngredients)
        textView.text = ingredientsText.toString()

        // Add more code to populate other UI components with additional drink details
    }
}