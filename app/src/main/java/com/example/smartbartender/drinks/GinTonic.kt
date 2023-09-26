package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class GinTonic : CocktailInterface {
    override val name: String = "Gin Tonic"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.gintonic
    init {
        ingredients["Gin"] = 10
        ingredients["Tonic"] = 50
        extraIngredients["Lemon"] = 1
    }
}