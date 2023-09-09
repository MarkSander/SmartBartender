package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Daiquiri : CocktailInterface {
    override val name: String = "Daiquiri"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.daiquiri
    init {
        ingredients["Rum"] = 60
        ingredients["Maple Syrup"] = 15
        extraIngredients["Lime"] = 2
        extraIngredients["Ice"] = 6
    }
}