package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Spritz : CocktailInterface {
    override val name: String = "Spritz"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.spritz
    init {
        ingredients["Prosecco"] = 50
        ingredients["Aperol"] = 50
        ingredients["Sparkling Water"] = 50
        extraIngredients["Ice"] = 6
        extraIngredients["Orange"] = 1
    }
}