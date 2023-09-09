package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class CubeLibre : CocktailInterface {
    override val name: String = "Cuba Libre"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.cubalibre
    init {
        ingredients["Witte rum"] = 30
        ingredients["Coke"] = 150
        extraIngredients["Lime"] = 1
        extraIngredients["Ice"] = 5
    }
}