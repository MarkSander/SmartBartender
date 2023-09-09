package com.example.smartbartender

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object Constants {
    // Arraylist and return the Arraylist
    suspend fun getDrinksData(inputString: String):ArrayList<Cocktail> = withContext(Dispatchers.IO) {
        val drinksList=ArrayList<Cocktail>()
        var urlString = "https://api.api-ninjas.com/v1/cocktail?ingredients="
        urlString += inputString
        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("accept", "application/json")
        connection.setRequestProperty("X-API-KEY", "JH2fbsuakdVRoIfKAKpHRQ==zcZTGJ3tMAIoQjx4")
        val responseStream: InputStream = connection.inputStream
        val jsonConfig = Json { ignoreUnknownKeys = true}
        val apiResponse: List<CocktailApiResponse> = jsonConfig.decodeFromString(responseStream.reader().readText())
        for (cocktailApiResponse in apiResponse) {
            val name = cocktailApiResponse.name
            val ingredients = cocktailApiResponse.ingredients

            val cocktail = Cocktail(name, ingredients)
            drinksList.add(cocktail)
        }

        drinksList
    }
}

@Serializable
data class CocktailApiResponse(
    val name: String,
    val ingredients: List<String>
)

