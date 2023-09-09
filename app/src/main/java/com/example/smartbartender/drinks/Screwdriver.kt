package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Screwdriver : CocktailInterface {
    override val name: String = "Screwdriver"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.screwdriver
    init {
        ingredients["Orange Juice"] = 100
        ingredients["Vodka"] = 50
        extraIngredients["Lemon"] = 1
        extraIngredients["Ice"] = 1
    }
}