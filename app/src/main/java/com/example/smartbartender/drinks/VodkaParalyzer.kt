package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class VodkaParalyzer : CocktailInterface {
    override val name: String = "Vodka Paralyzer"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.vodkaparalyzer
    init {
        ingredients["Vodka"] = 30
        ingredients["Club Soda"] = 150
        ingredients["Coke"] = 30
        extraIngredients["Ice"] = 3
    }
}