package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Vesper : CocktailInterface {
    override val name: String = "Vesper"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.vesper
    init {
        ingredients["Vodka"] = 30
        ingredients["Gin"] = 90
        extraIngredients["Lemon"] = 1
        extraIngredients["Ice"] = 5
    }
}