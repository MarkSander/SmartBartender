package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class WhiskySour : CocktailInterface {
    override val name: String = "Whisky Sour"
    override val ingredients: MutableMap<String, Int> = HashMap()
    val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.whiskysour
    init {
        ingredients["Whisky"] = 60
        ingredients["Maple Syrup"] = 20
        extraIngredients["Egg white"] = 2
        extraIngredients["Ice"] = 10
    }
}