package com.example.smartbartender

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class RasberryHttpRequests{
    private val url = "http://192.168.68.75:5000"

    fun sendNewCocktailListHttpRequestAsync(newCocktailList: MutableList<CocktailInterface>) {
        val newUrl = "$url/cocktailsave/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val jsonArray = JsonArray()
                val gson = Gson()

                for (cocktail in newCocktailList) {
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

                val requestBody = jsonArray.toString().toRequestBody("application/json".toMediaType())
                Log.d("Request info", "Sending the following request body: $requestBody")
                val request = Request.Builder()
                    .url(newUrl)
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

    suspend fun sendHttpCocktailListRequestAsync(): List<CocktailInterface>? {
        val newUrl = "$url/cocktailrequest/"
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(newUrl)
                    .build()

                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    Log.d("HttpRequest", "Response body received: $responseBody")

                    // Parse the JSON response into a List<Cocktail>
                    val gson = Gson()
                    val cocktailList: List<Cocktail>? = gson.fromJson(
                        responseBody,
                        object : TypeToken<List<Cocktail>>() {}.type
                    )

                    // Convert List<Cocktail> to List<CocktailInterface>
                    cocktailList?.map { cocktail ->
                        Cocktail(
                            name = cocktail.name,
                            imageResourceId = cocktail.imageResourceId,
                            ingredients = cocktail.ingredients.toMutableMap(),
                            extraIngredients = cocktail.extraIngredients.toMutableMap()
                        ) as CocktailInterface
                    }
                } else {
                    Log.e("HttpRequest", "Failed to retrieve data: ${response.code}")
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
    // Function to parse the JSON response and convert it to a list of CocktailInterface

    data class CocktailResponse(val cocktail: List<Cocktail>)

    fun sendNewPumpHttpRequestAsync(pump1: String, pump2: String, pump3: String, pump4: String) {
        val newUrl = "$url/pumpchange/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val json = buildJsonObject {
                    put("pump1", pump1)
                    put("pump2", pump2)
                    put("pump3", pump3)
                    put("pump4", pump4)
                }
                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(newUrl)
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

    fun sendFillAndFlushHttpRequestAsync(pump1: Int, pump2: Int, pump3: Int, pump4: Int): String? {
        val newUrl = "$url/$pump1/$pump2/$pump3/$pump4"

        // Use runBlocking to launch a coroutine for the network request
        return runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(newUrl)
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

    fun sendHttpRequestAsync() {
        val url = "$url/pumprequest/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
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
                    Log.d("HttpRequest", "Response body received: $responseBody")

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
            val jsonObject= JSONObject(result)
            drinkInput1 = jsonObject.optString("pump1drink", "Empty")
            selectedDrinkPosition1 = settingsInputDrinks.indexOf(drinkInput1)
            drinkInput2 = jsonObject.optString("pump2drink", "Empty")
            selectedDrinkPosition2 = settingsInputDrinks.indexOf(drinkInput2)
            drinkInput3 = jsonObject.optString("pump3drink", "Empty")
            selectedDrinkPosition3 = settingsInputDrinks.indexOf(drinkInput3)
            drinkInput4 = jsonObject.optString("pump4drink", "Empty")
            selectedDrinkPosition4 = settingsInputDrinks.indexOf(drinkInput4)

        }
    }

    fun sendHttpHomeViewRequestAsync() {
        val newUrl = "$url/pumprequest/"


        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
            val deferredResult = async(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(newUrl)
                    .build()

                try {
                    val response = client.newCall(request).execute()
                    // Handle the response as needed
                    val responseBody = response.body?.string()
                    // Process the response data here
                    Log.d("HttpRequest", "Response body received: $responseBody")

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
                val jsonObject= JSONObject(result)
                homeInput1 = jsonObject.optString("pump1drink", "Empty")
                homeInput2 = jsonObject.optString("pump2drink", "Empty")
                homeInput3 = jsonObject.optString("pump3drink", "Empty")
                homeInput4 = jsonObject.optString("pump4drink", "Empty")


        }
    }

    fun sendDrinkDetailHttpRequestAsync() {
        val url = "$url/pumprequest/"

        // Use runBlocking to launch a coroutine for the network request
        runBlocking {
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
                    Log.d("HttpRequest", "Response body received: $responseBody")

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
            val jsonObject= JSONObject(result)
            detailDrinkInput1 = jsonObject.optString("pump1drink", "Empty")
            detailDrinkInput2 = jsonObject.optString("pump2drink", "Empty")
            detailDrinkInput3 = jsonObject.optString("pump3drink", "Empty")
            detailDrinkInput4 = jsonObject.optString("pump4drink", "Empty")
        }
    }

    fun sendDrinkHttpRequestAsync(pump1: Int, pump2: Int, pump3: Int, pump4: Int): String? {
        val url = "$url/$pump1/$pump2/$pump3/$pump4"

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