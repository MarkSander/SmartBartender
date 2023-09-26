package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class BlueLagoon : CocktailInterface {
    override val name: String = "Blue Lagoon"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.bluelagoon
    init {
        ingredients["Lemon Juice"] = 20
        ingredients["Vodka"] = 30
        ingredients["Blue Curacao"] = 20
        ingredients["Sprite"] = 100
        extraIngredients["Ice"] = 3
        extraIngredients["Lemon"] = 1
    }
}