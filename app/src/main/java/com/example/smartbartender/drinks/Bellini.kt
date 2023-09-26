package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Bellini : CocktailInterface {
    override val name: String = "Bellini"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.bellini
    init {
        ingredients["Prosecco"] = 80
        ingredients["Peach Juice"] = 40
        extraIngredients["Peach"] = 1
        extraIngredients["Ice"] = 4
    }
}