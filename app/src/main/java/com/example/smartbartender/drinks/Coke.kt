package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Coke : CocktailInterface {
    override val name: String = "Coke"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.coke
    init {
        ingredients["Coke"] = 150
        extraIngredients["Ice"] = 5
    }
}