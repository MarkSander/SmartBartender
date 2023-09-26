package com.example.smartbartender.drinks

import com.example.smartbartender.CocktailInterface
import com.example.smartbartender.R

class AmericanCocktail : CocktailInterface {
    override val name: String = "American Cocktail"
    override val ingredients: MutableMap<String, Int> = HashMap()
    override val extraIngredients: MutableMap<String, Int> = HashMap()
    override val imageResourceId = R.drawable.americancocktail
    init {
        ingredients["Peach Juice"] = 30
        ingredients["Vodka"] = 40
        ingredients["Blue Curacao"] = 20
        ingredients["Sprite"] = 100
        extraIngredients["Ice"] = 5
    }
}