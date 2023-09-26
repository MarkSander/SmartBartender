package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class DisaronnoSour : CocktailInterface {
    override val name: String = "Disaronno Sour"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.disaronnosour
    init {
        ingredients["Amaretto"] = 50
        ingredients["Lemon Juice"] = 40
        ingredients["Maple Syrup"] = 20
        extraIngredients["Egg White"] = 1
        extraIngredients["Lemon"] = 1
    }
}