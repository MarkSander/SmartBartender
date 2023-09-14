package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class Mojito : CocktailInterface {
    override val name: String = "Mojito"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.mojito
    init {
        ingredients["Witte rum"] = 45
        ingredients["Bruiswater"] = 30
        extraIngredients["Lemon"] = 4
        extraIngredients["Suiker"] = 2
        extraIngredients["Munt"] = 1
    }
}