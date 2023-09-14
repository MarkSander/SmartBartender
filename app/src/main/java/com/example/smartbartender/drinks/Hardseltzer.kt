package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Hardseltzer : CocktailInterface {
    override val name: String = "Hardseltzer"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.hardseltzer
    init {
        ingredients["Vodka"] = 40
        ingredients["Sparkling Water"] = 280
        extraIngredients["Lemon"] = 1
    }
}