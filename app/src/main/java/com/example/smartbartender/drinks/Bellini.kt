package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Bellini : CocktailInterface {
    override val name: String = "Bellini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.bellini
    init {
        ingredients["Prosecco"] = 40
        ingredients["Sinaasapplesap"] = 200
        extraIngredients["Perzik"] = 6
        extraIngredients["Ice"] = 4
    }
}