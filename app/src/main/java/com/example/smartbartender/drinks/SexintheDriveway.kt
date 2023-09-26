package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class SexintheDriveway : CocktailInterface {
    override val name: String = "Sex in the Driveway"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.sexinthedriveway
    init {
        ingredients["Vodka"] = 40
        ingredients["Peach Juice"] = 25
        ingredients["Sprite"] = 110
        ingredients["Blue Curacao"] = 25
        extraIngredients["Ice"] = 2
    }
}