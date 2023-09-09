package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class BloodyMary : CocktailInterface {
    override val name: String = "Bloody Mary"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.bloodymary
    init {
        ingredients["Tomato juice"] = 125
        ingredients["Vodka"] = 50
        extraIngredients["Lemon juice"] = 10
        extraIngredients["Lemon"] = 1
    }
}