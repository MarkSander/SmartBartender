package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class AmarettoSour : CocktailInterface {
    override val name: String = "Amaretto Sour"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.amarettosour
    init {
        ingredients["Amaretto"] = 45
        ingredients["Whisky"] = 15
        extraIngredients["Lemon"] = 1
        extraIngredients["Ice"] = 6
    }
}