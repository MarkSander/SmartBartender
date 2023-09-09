package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class GinTonic : CocktailInterface {
    override val name: String = "Gin Tonic"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.gintonic
    init {
        ingredients["Gin"] = 45
        ingredients["Tonic"] = 20
        extraIngredients["Citroen"] = 1
    }
}