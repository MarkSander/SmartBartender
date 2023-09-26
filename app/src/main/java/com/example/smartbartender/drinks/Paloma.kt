package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Paloma : CocktailInterface {
    override val name: String = "Paloma"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.paloma
    init {
        ingredients["Tequilla"] = 40
        ingredients["Spa Grapefruit"] = 80
        extraIngredients["Lime"] = 2
    }
}