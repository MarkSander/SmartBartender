package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class MoscowMule : CocktailInterface {
    override val name: String = "Moscow Mule"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.moscowmule
    init {
        ingredients["Vodka"] = 50
        ingredients["Ginger Beer"] = 250
        extraIngredients["Lemon"] = 5
        extraIngredients["Munt"] = 2
        extraIngredients["Ice"] = 3
    }
}