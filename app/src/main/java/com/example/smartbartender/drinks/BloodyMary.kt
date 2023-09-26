package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class BloodyMary : CocktailInterface {
    override val name: String = "Bloody Mary"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.bloodymary
    init {
        ingredients["Tomato juice"] = 120
        ingredients["Vodka"] = 45
        extraIngredients["Lemon juice"] = 15
        extraIngredients["Lemon"] = 1
    }
}