package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Appletini : CocktailInterface {
    override val name: String = "Appletini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.appletini
    init {
        ingredients["Vodka"] = 60
        ingredients["Apple Juice"] = 60
        ingredients["Maple Syrup"] = 20
        ingredients["Lemon Juice"] = 20
        extraIngredients["Apple"] = 1
    }
}