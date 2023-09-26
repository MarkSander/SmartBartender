package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Mojito : CocktailInterface {
    override val name: String = "Mojito"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.mojito
    init {
        ingredients["Rum"] = 50
        ingredients["Sparkling Water"] = 50
        ingredients["Lemon Juice"] = 25
        extraIngredients["Sugar"] = 2
        extraIngredients["Munt"] = 1
        extraIngredients["Ice"] = 3
    }
}