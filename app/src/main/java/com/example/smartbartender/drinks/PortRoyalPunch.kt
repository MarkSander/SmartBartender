package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class PortRoyalPunch : CocktailInterface {
    override val name: String = "Port Royal Punch"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.portroyalpunch
    init {
        ingredients["Rum"] = 40
        ingredients["Pineapple Juice"] = 60
        ingredients["Maple Syrup"] = 10
        ingredients["Sprite"] = 60
        extraIngredients["Ice"] = 3
        extraIngredients["Orange"] = 1
    }
}