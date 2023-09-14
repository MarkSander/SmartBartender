package com.example.smartbartender

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.Window
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smartbartender.GridViewModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

class DrinkDetailActivity : AppCompatActivity() {
    private lateinit var drinksViewModel: DrinksViewModel
    // Access appPreferences in your activities or fragments
    private lateinit var appPreferences: AppPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform)
            window.sharedElementEnterTransition = transition
        }
        setContentView(R.layout.detail_fragment)
        appPreferences = (applicationContext as MyApplication).appPreferences
        drinksViewModel = ViewModelProvider(this).get(DrinksViewModel::class.java)

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
                        pump1 -> pump1Value = ingredientValue
                        pump2 -> pump2Value = ingredientValue
                        pump3 -> pump3Value = ingredientValue
                        pump4 -> pump4Value = ingredientValue
                        else -> {
                            Log.e("Match Error", "Ingredient $ingredientName did not match any pump value")
                            // Handle the case where the ingredient doesn't match any pump
                        }
                    }
                }
            } else{
                Log.e("Ingredient error","No Ingredients found")
            }
            GlobalScope.launch(Dispatchers.IO) {

                Log.i("Info", "Request being send to pump api localhost/$pump1Value/$pump2Value/$pump3Value/$pump4Value")
                //TODO Decomment below if connected to rasberry
                val result = sendHttpRequestAsync(pump1Value, pump2Value, pump3Value, pump4Value)

                // Check if the result contains "Finished"
                if (result == "Finished") {
                    // Perform actions after receiving "Finished"
                    // This code will run on a background thread

                    // If you need to update UI elements, use withContext(Dispatchers.Main) {}
                    // Example: withContext(Dispatchers.Main) { updateUI() }
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

        val sharedImageView = findViewById<ImageView>(R.id.drinkImage)
        ViewCompat.setTransitionName(sharedImageView, "drinkImageTransition")

        // Populate UI components with drink information
        drinkImageView.setImageResource(drinkImageResource)
        drinkNameView.text = drinkName
        val textView = findViewById<TextView>(R.id.drinkIngredients)
        textView.text = ingredientsText.toString()
        val extraTextView = findViewById<TextView>(R.id.extraDrinkIngredients)
        extraTextView.text = extraIngredientsText.toString()

        // Add more code to populate other UI components with additional drink details
    }

    private fun sendHttpRequestAsync(pump1: Int, pump2: Int, pump3: Int, pump4: Int): String? {
        val url = "http://192.168.234.144:5000/$pump1/$pump2/$pump3/$pump4"

        // Use runBlocking to launch a coroutine for the network request
        return runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    // Handle the response as needed
                    val responseBody = response.body?.string()
                    // Process the response data here

                    // Return the result if needed
                    responseBody
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle the exception
                    null // Return null in case of an error
                }
            }

            // You can use await to get the result later if needed
            val result = deferredResult.await()
            // Process the result or use it as needed
            result
        }
    }

}
