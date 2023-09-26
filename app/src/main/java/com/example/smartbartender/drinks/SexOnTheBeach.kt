package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class SexOnTheBeach : CocktailInterface {
    override val name: String = "Sex on The Beach"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.sexonthebeach
    init {
        ingredients["Vodka"] = 30
        ingredients["Peach Juice"] = 15
        ingredients["Orange Juice"] = 60
        ingredients["Cranberry Juice"] = 50
        extraIngredients["Orange"] = 1
        extraIngredients["Ice"] = 4
    }
}