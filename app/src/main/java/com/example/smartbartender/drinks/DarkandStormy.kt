package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class DarkandStormy : CocktailInterface {
    override val name: String = "Dark and Stormy"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.darkandstormy
    init {
        ingredients["Rum"] = 60
        ingredients["Ginger Beer"] = 90
        extraIngredients["Lemon"] = 1
        extraIngredients["Ice"] = 5
    }
}