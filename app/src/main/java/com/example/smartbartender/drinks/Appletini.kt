package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Appletini : CocktailInterface {
    override val name: String = "Appletini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.appletini
    init {
        ingredients["Vodka"] = 50
        ingredients["Apple Juice"] = 50
        ingredients["Maple Syrup"] = 50
        extraIngredients["LemonJuice"] = 10
        extraIngredients["Apple"] = 1
    }
}