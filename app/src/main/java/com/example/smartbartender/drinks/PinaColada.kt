package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class PinaColada : CocktailInterface {
    override val name: String = "Pina Colada"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.pinacolada
    init {
        ingredients["Rum"] = 50
        ingredients["Kokos Water"] = 35
        ingredients["Pineapple Juice"] = 35
        extraIngredients["Pineapple"] = 1
        extraIngredients["Sugar"] = 1
    }
}