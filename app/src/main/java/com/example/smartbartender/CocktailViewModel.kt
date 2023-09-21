package com.example.smartbartender

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val Context.dataStore by preferencesDataStore("myDataStore")
private val rasberryRequests = RasberryHttpRequests()
class CocktailViewModel() : ViewModel() {
    //class CocktailViewModel() : ViewModel() {
    //private val dataSource = context.dataStore
    var cocktailList: MutableList<CocktailInterface> = mutableListOf()
    val rasberryHttpRequests = RasberryHttpRequests()

/*    val myData: Flow<List<CocktailInterface>> = dataSource.data.map {
        preferences ->
        val jsonString = preferences[PreferencesKeys.LIST_KEY]
        val cocktails = Gson().fromJson(jsonString, List::class.java)
        cocktails as? List<CocktailInterface> ?: emptyList()
    }*/
    init {
    val result: List<CocktailInterface>? = runBlocking {
        rasberryHttpRequests.sendHttpCocktailListRequestAsync()
    }

    // Check if the result is not null and assign it to cocktailList
    if (result != null) {
        cocktailList = result.toMutableList()
    } else {
        cocktailList = mutableListOf()
    }
    }

    fun addNewCocktail(cocktail: CocktailInterface): Boolean {
        val nonEmptyIngredients = cocktail.filterNonEmptyIngredients()
        if (nonEmptyIngredients.isNotEmpty() && cocktail.name.isNotEmpty()) {
            val totalQuantity = nonEmptyIngredients.values.sum()
            if (totalQuantity <= 200) {
                cocktailList.add(Cocktail(cocktail.name, nonEmptyIngredients, cocktail.extraIngredients, cocktail.imageResourceId))
                rasberryRequests.sendNewCocktailListHttpRequestAsync(cocktailList)
                return true
            } else {
                return false
                // Notify the user that the total quantity exceeds 200
                // You can display a message, show a toast, or handle it as needed
                // For example, you can use a Toast to display a message:
                // Toast.makeText(context, "Total quantity exceeds 200. Cocktail not added.", Toast.LENGTH_SHORT).show()
            }
        }
        return false
        //saveCocktailList()
    }

    private fun CocktailInterface.filterNonEmptyIngredients(): MutableMap<String, Int> {
        val filteredIngredients = mutableMapOf<String, Int>()
        for ((ingredient, quantity) in this.ingredients) {
            if (!ingredient.contains("Empty", ignoreCase = true)) {
                filteredIngredients[ingredient] = quantity
            }
        }
        return filteredIngredients
    }

    private fun sendNewCocktailListHttpRequestAsync(newCocktailList: MutableList<CocktailInterface>) {
        val url = "http://192.168.233.144:5000/pumpchange/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val jsonArray = JsonArray()
                val gson = Gson()

                for (cocktail in cocktailList) {
                    val jsonObject = JsonObject()
                    jsonObject.addProperty("name", cocktail.name)
                    jsonObject.addProperty("imageResourceId", cocktail.imageResourceId)

                    val ingredientsObject = JsonObject()
                    cocktail.ingredients.forEach { (ingredient, quantity) ->
                        ingredientsObject.addProperty(ingredient, quantity)
                    }
                    jsonObject.add("ingredients", ingredientsObject)

                    val extraIngredientsObject = JsonObject()
                    cocktail.extraIngredients.forEach { (ingredient, quantity) ->
                        extraIngredientsObject.addProperty(ingredient, quantity)
                    }
                    jsonObject.add("extraIngredients", extraIngredientsObject)

                    jsonArray.add(jsonObject)
                }

// Now jsonArray contains your list of cocktails as JSON objects
// You can use this jsonArray in your POST request

                val requestBody = jsonArray.toString().toRequestBody("application/json".toMediaType())
                Log.d("Request info", "Sending the following request body: $requestBody")
                val request = Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .post(requestBody)
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    // Handle the response as needed
                    val responseBody = response.body?.string()
                    // Process the response data here
                    Log.e("HttpRequest", "Response body received: $responseBody")

                    // Return the result if needed
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        // Process the response body
                    } else {
                        // Handle the error
                        println("HTTP Error: ${response.code}")
                        println("Error Message: ${response.body?.string()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle the exception
                    null // Return null in case of an error
                }
            }

            // You can use await to get the result later if needed
            val result = deferredResult.await()

        }
    }


    /*
        private suspend fun saveCocktailList() {
    */
/*        val jsonString = Gson().toJson(cocktailList)
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.LIST_KEY] = jsonString
            }
        }*//*

*/
/*        dataStore.updateData { data ->
            data.toBuilder().
        }*//*

        dataSource.edit { preferences -> preferences[PreferencesKeys.LIST_KEY] = cocktailList.toString() }
    }

    private suspend fun <T> getCocktailList(defaultValue: T) {
//        Flow<T> = dataSource.data.catch {
//            exception ->
//            if (exception is IOException){
//                emit(emptyPreferences())
//            } else {
//                throw exception
//            }
//        }.map { preferences ->
//            val result = preferences[PreferencesKeys.LIST_KEY]?: defaultValue
//            result
//        }
    }
*/

/*    private object PreferencesKeys {
        val LIST_KEY = stringPreferencesKey("cocktail_list")
    }*/
}



