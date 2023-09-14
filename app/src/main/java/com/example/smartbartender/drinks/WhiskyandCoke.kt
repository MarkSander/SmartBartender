package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class WhiskyandCoke : CocktailInterface {
    override val name: String = "Whisky and Coke"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.whiskeyandcoke
    init {
        ingredients["Whisky"] = 35
        ingredients["Coke"] = 200
        extraIngredients["Ice"] = 6
        extraIngredients["Lemon"] = 1
    }
}